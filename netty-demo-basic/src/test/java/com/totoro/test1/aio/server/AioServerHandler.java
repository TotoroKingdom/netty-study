package com.totoro.test1.aio.server;

import com.totoro.test1.aio.ChannelAdapter;
import com.totoro.test1.aio.ChannelHandler;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class AioServerHandler extends ChannelAdapter {
    public AioServerHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("客户端ctx=="+ctx.channel().getRemoteAddress());
            ctx.writeAndFlush("与服务器建立连接成功"+ LocalDateTime.now() + "" + ctx.channel().getRemoteAddress() + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("客户端发来的消息："+msg+" \r\n");
        ctx.writeAndFlush("服务器成功收到消息 \r\n");

    }
}
