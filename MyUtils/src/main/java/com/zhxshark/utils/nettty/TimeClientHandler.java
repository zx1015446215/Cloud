package com.zhxshark.utils.nettty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当客户端和服务端TCP链路建立成功之后，Netty的NIO线程会调用channelActive方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("调用channelActive");
        String reqMsg = "我是客户端"+Thread.currentThread().getName();
        byte[] reqMsgByte = reqMsg.getBytes("UTF-8");
        ByteBuf byteBuf = Unpooled.buffer(reqMsgByte.length);
        byteBuf.writeBytes(reqMsgByte);
        System.out.println("客户端发送:"+reqMsg);
        ctx.writeAndFlush(byteBuf);
        System.out.println("ok");
    }


    /**
     * 当收到应答消息的时候，调用channelRead消息，从netty的ByteBuf中读取并打印应答消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("进入channelRead");
        ByteBuf byteBuf = (ByteBuf)msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        System.out.println(Thread.currentThread().getName()+" Sever return Message:"+new String(bytes,"UTF-8"));
        ctx.close();
    }

    /**
     * 当发生异常的时候，打印异常日志，释放客户端资源
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /** 释放资源 */
        System.out.println("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}
