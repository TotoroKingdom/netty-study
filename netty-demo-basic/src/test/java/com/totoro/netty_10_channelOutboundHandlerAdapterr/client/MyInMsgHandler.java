package com.totoro.netty_10_channelOutboundHandlerAdapterr.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class MyInMsgHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("客户端链接到服务端 channelId==" + channel.id());
        System.out.println("remote port==" + channel.localAddress().getPort());
        String res = new Date() + "通知服务器建立连接成功\r\n";
        ctx.writeAndFlush(res);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接" + ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "客户端收到消息：" + msg );
        String res = "随机数：" + Math.random() * 10 + "\r\n";
        ctx.writeAndFlush(res);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }
}
