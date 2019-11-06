package com.zhxshark.utils.nettty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * NioSocketChannel，异步的客户端 TCP Socket 连接。
 * NioServerSocketChannel，异步的服务器端 TCP Socket 连接。
 * NioDatagramChannel，异步的 UDP 连接。
 * NioSctpChannel，异步的客户端 Sctp 连接。
 * NioSctpServerChannel，异步的 Sctp 服务器端连接，这些通道涵盖了 UDP 和 TCP 网络 IO 以及文件 IO。
 *
 */


public class TimeServer {
    public static void main(String[] args) {
        TimeServer timeServer = new TimeServer();
        timeServer.bind(9898);

    }

    /**
     * bossGroup轮询执行的任务包括3类:
     * 1.轮询Accept任务
     * 2.处理Accept I/O事件，与client建立连接，workerGroup
     * 3.处理任务队列中的任务，runAllTasks。任务队列中的任务包括用户调用 eventloop.execute 或 schedule 执行的任务，或者其他线程提交到该 eventloop 的任务。
     *
     * workerGroup轮询执行的任务包括3类
     * 1.轮询read/write事件
     * 2.处理I/O事件，即Read、Write事件，在NioSocketChannel可读、可写发生时进行处理
     * 3.处理任务队列中的任务，runAllTasks
     */
    public void bind(int port){
        //配置服务端的NIO线程，用于网络事件，实质上是reactor
        //bossGrop用于服务端接收客户端连接，workerGroup用于SocketChannel网络读写
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());
            /** 服务器启动服装类配置完成之后，调用bind绑定端口号,调用sync方法异步等待方法绑定完成*/
            ChannelFuture f = b.bind(port).sync();
            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.........");
            /**下面会进行阻塞，等待服务器连接关闭之后 main 方法退出，程序结束
             *
             * */
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            arg0.pipeline().addLast(new TimeServerHandler());
        }
    }

}
