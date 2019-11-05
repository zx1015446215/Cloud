package com.zhxshark.utils.nettty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

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
                        .option(ChannelOption.TCP_NODELAY,true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                            }
                        });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void run() {
            connect("127.0.0.1",8888);
        }
    }


}
