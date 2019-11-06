package com.zhxshark.utils.nettty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            new Thread(new MyThread()).start();
        }
    }
    static class MyThread implements Runnable{

        public void connect(String host,int port){
            NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(eventExecutors)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY,true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new TimeClientHandler());
                            }
                        });
                ChannelFuture channelFuture = b.connect(host, port).sync();
                System.out.println(Thread.currentThread().getName() + ",客户端发起异步连接..........");

                /**
                 * 等待客户端链路关闭
                 */
                channelFuture.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                eventExecutors.shutdownGracefully();
            }

        }

        public void run() {
            connect("127.0.0.1",9898);
        }
    }


}
