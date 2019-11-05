package com.zhxshark.utils.nettty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TimeServerHandler extends SimpleChannelInboundHandler {
    /**
     * 收到客户端的消息，自动触发
     * @param channelHandlerContext
     * @param o
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext chc, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf)msg;

        byte[] reg = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(reg);

        String body = new String(reg,"UTF-8");
        System.out.println(Thread.currentThread().getName()+ ",The server receive  order : " + body);

        /**
         * 回复消息
         * copiedBuffer：创建一个新的缓冲区，内容为里面的参数
         * 通过 ChannelHandlerContext 的 write 方法将消息异步发送给客户端
         *
         */
        String respMsg = "I am Server , 消息接收Success";
        ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
        chc.write(respByteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**flush：将消息发送队列中的消息写入到 SocketChannel 中发送给对方，为了频繁的唤醒 Selector 进行消息发送
         * Netty 的 write 方法并不直接将消息写如 SocketChannel 中，调用 write 只是把待发送的消息放到发送缓存数组中，再通过调用 flush
         * 方法，将发送缓冲区的消息全部写入到 SocketChannel 中
         * */
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /** 当发生异常时，关闭ChannelHandlerContext，释放和它相关联的句柄资源*/
        ctx.close();
    }
}
