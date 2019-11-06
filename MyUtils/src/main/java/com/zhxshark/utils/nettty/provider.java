package com.zhxshark.utils.nettty;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class provider {

    /*标识数字*/
    private static int flag = 0;
    /*缓冲区大小*/
    private static int BLOCK = 4096;
    /*接受数据缓冲区*/
    private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    /*服务器端地址*/
    private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress(
            "localhost", 9999);

    public static void main(String[] args) throws IOException, InterruptedException {
        //打开socket通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //打开选择器
        Selector selector = Selector.open();
        //注册连接服务的socket动作
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        //连接
        socketChannel.connect(SERVER_ADDRESS);
        // 分配缓冲区大小内存

        Set<SelectionKey> selectionKeys;
        Iterator<SelectionKey> iterator;
        SelectionKey selectionKey;
        SocketChannel client;
        String receiveText;
        String sendText;
        int count=0;

        while (true){
            Thread.sleep(1000);
            selector.select();
            //返回此选择器已经选择的键集
            selectionKeys = selector.selectedKeys();
            iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                selectionKey = iterator.next();
                if(selectionKey.isConnectable()){
                    System.out.println("client connect");
                    client = (SocketChannel)selectionKey.channel();
                    //判断此通道上是否正在进行连接操作
                    //完成套接字通道的连接过程
                    if (client.isConnectionPending()){
                        client.finishConnect();
                        System.out.println("完成连接");
                        sendbuffer.clear();
                        sendText = new Date()+" Hello server";
                        sendbuffer.put(sendText.getBytes());
                        sendbuffer.flip();
                        client.write(sendbuffer);
                        System.out.println("客户端想服务端发送数据:"+sendText);
                    }
                    client.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    client = (SocketChannel)selectionKey.channel();
                    receivebuffer.clear();
                    count = client.write(receivebuffer);
                    if(count>0){
                        receiveText = new String(receivebuffer.array(),0,count);
                        System.out.println(count+"客户端接受服务器数据:"+receiveText);
                        client.register(selector,SelectionKey.OP_WRITE);
                    }
                }else if(selectionKey.isWritable()){
                    sendbuffer.clear();
                    client = (SocketChannel)selectionKey.channel();
                    sendText = new Date()+" Hello server";
                    sendbuffer.put(sendText.getBytes());
                    sendbuffer.flip();
                    client.write(sendbuffer);
                    System.out.println("客户端想服务端发送数据:"+sendText);
                    client.register(selector,SelectionKey.OP_READ);
                }
            }
            selectionKeys.clear();
        }

    }
}
