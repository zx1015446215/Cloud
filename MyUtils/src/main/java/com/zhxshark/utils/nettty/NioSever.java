package com.zhxshark.utils.nettty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * SelectionKey.OP_ACCEPT —— 接收连接继续事件，表示服务器监听到了客户连接，服务器可以接收这个连接了
 * SelectionKey.OP_CONNECT —— 连接就绪事件，表示客户与服务器的连接已经建立成功
 * SelectionKey.OP_READ —— 读就绪事件，表示通道中已经有了可读的数据，可以执行读操作了（通道目前有数据，可以进行读操作了）
 * SelectionKey.OP_WRITE —— 写就绪事件，表示已经可以向通道写数据了（通道目前可以用于写操作）
 */

public class NioSever {

    /**表示数字*/
    private int flag = 0;
    /**缓冲区大小*/
    private int BLOCK = 4096;
    /**接收数据缓冲区*/
    private ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
    /**发送数据缓冲区*/
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);

    private Selector selector;

    /**
     * 注册selector并等待连接
     * @param port
     * @throws IOException
     */
    public NioSever(int port) throws IOException {
        //打开服务套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //服务器配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //检查与此通道的套接字
        ServerSocket socket = serverSocketChannel.socket();
        //进行服务的绑定
        socket.bind(new InetSocketAddress(9999));
        //通过open方法找到selector
        selector = Selector.open();
        //注册到selector等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 监听
     */
    private void listen() throws IOException {
        while (true){
            //检测是否有新的连接
            if (selector.select()>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    handleKey(selectionKey);
                }
            }
        }
    }

    /**
     * 处理请求
     * @param selectionKey
     */
    private void handleKey(SelectionKey selectionKey) throws IOException {
        // 接受请求
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count = 0;
        //测试此键的通道是否准备好接收新的套接字连接
        if (selectionKey.isAcceptable()){
            //返回此前为之创建的通道
            server = (ServerSocketChannel) selectionKey.channel();
            //接收到此通道套接字的连接
            client = server.accept();
            //配置为非阻塞
            client.configureBlocking(false);
            //注册到selector，等待连接
            client.register(selector, SelectionKey.OP_READ);
        }else if(selectionKey.isReadable()){
            //返回此前为之创建的通道
            client = (SocketChannel) selectionKey.channel();
            //将缓冲区清空以备下一次读取
            receiveBuffer.clear();
            //获取服务器发送来的数据到缓冲区
            count = client.read(receiveBuffer);
            if (count >0 ){
                receiveText = new String(receiveBuffer.array(),0,count);
                System.out.println(count+"服务器接收客户端数据为:"+receiveText);
                client.register(selector,SelectionKey.OP_WRITE);
            }
        }else if(selectionKey.isWritable()){
            //清空发送缓冲区
            sendBuffer.clear();
            client = (SocketChannel)selectionKey.channel();
            sendText = "message from server"+flag++;
            //向缓冲区输入数据
            sendBuffer.put(sendText.getBytes());
            //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
            sendBuffer.flip();
            //输出到通道
            client.write(sendBuffer);
            System.out.println("服务器向客户端发送数据:"+sendText);
            client.register(selector,SelectionKey.OP_READ);
        }

    }



    public static void main(String[] args) throws IOException {
        int port = 9999;
        NioSever sever = new NioSever(port);
        sever.listen();
    }
}
