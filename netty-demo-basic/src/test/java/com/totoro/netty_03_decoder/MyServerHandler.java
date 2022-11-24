package com.totoro.netty_03_decoder;

import io.netty.channel.Channel;
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
        ctx.writeAndFlush("你好，恭喜你连接服务器成功！\r\n");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(LocalDateTime.now() + ": " + msg);
    }
}
