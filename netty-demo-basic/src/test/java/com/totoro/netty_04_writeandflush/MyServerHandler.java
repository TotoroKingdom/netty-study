package com.totoro.netty_04_writeandflush;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/11/24
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("有客户端连接成功："+channel.localAddress().getHostString());
        System.out.println("port："+channel.localAddress().getPort());

        String response = "你好，恭喜你连接服务器成功！\r\n";
        ByteBuf buffer = Unpooled.buffer(response.getBytes().length);
        buffer.writeBytes(response.getBytes("GBK"));
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接："+ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到客户端的消息：" + LocalDateTime.now() + ":" + msg);

        String response = "我收到消息了！\r\n";
        ByteBuf buffer = Unpooled.buffer(response.getBytes().length);
        buffer.writeBytes(response.getBytes("GBK"));
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
