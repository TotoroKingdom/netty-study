package com.totoro.netty_08_client_handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;


/**
 * @author:totoro
 * @createDate:2022/12/6
 * @description:
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("channelId==" + channel.id());
        System.out.println("remote=="  + channel.remoteAddress().getPort());
        String res = "恭喜你，连接成功\r\n";
        ctx.writeAndFlush(res);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接"+ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到消息：" + msg);
        String res = "客户端成功收到消息";
        ctx.writeAndFlush(res);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel();
        System.out.println(cause.getMessage());
    }
}
