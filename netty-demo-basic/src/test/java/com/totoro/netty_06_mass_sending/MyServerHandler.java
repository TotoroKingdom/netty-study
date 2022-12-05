package com.totoro.netty_06_mass_sending;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/12/5
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ChannelHandler.channelGroup.add(ctx.channel());

        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("有客户端连接成功："+channel.remoteAddress().getHostString());
        System.out.println("port："+channel.remoteAddress().getPort());

        String response = "你好，恭喜你连接服务器成功！\r\n";
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接："+ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("收到客户端的消息：" + LocalDateTime.now() + ":" + msg);

        String res = channel.remoteAddress().getPort() + ": " + msg;
        ChannelHandler.channelGroup.writeAndFlush(res);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息"+cause.getMessage());
    }
}
