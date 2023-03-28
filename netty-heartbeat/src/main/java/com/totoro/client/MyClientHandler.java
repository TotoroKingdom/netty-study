package com.totoro.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:totoro
 * @createDate:2023/3/28
 * @description:
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("连接到服务器：" + channel.id());
        System.out.println("链接报告：" + channel.localAddress().getHostString());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开连接重连" + ctx.channel().localAddress().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new NettyClient().connect("127.0.0.1",7397);
                    System.out.println("断开连接：netty client start done");
                    Thread.sleep(500);
                } catch (Exception e){
                    System.out.println("netty client start error go reconnect");
                }

            }
        }).start();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date() + "接收到消息：" + msg));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("断开重连：" + cause.getMessage());
        ctx.close();
    }
}
