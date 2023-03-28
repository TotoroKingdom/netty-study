package com.totoro.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:totoro
 * @createDate:2023/3/28
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("IP: " + channel.localAddress().getHostString());

        String res = "通知客户端建立连接成功" + new Date() + "\r\n";

        ctx.writeAndFlush(res);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接：" + ctx.channel().localAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "接收到消息：" + msg);
        String res = "服务端收到：" + msg +"\r\n";
        ctx.writeAndFlush(res);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent){

            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE){
                System.out.println("Reader Idle");
                ctx.writeAndFlush("读取等待。。。。\r\n");
                ctx.close();
            } else if(e.state() == IdleState.WRITER_IDLE){
                System.out.println("Write Idle....");
                ctx.writeAndFlush("写入等待： \r\n");
            } else if (e.state() == IdleState.ALL_IDLE){
                System.out.println("All Idle...");
                ctx.writeAndFlush("全部时间： \r\n");
            }
        }
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息： \r\n" + cause.getMessage());
    }
}
