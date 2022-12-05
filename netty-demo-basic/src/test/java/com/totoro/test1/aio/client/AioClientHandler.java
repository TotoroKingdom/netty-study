package com.totoro.test1.aio.client;

import com.totoro.test1.aio.ChannelAdapter;
import com.totoro.test1.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/11/17
 * @description:
 */
public class AioClientHandler extends ChannelAdapter {


    public AioClientHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {

        try {
            System.out.println("ctx:" + ctx.channel().getRemoteAddress());
            ctx.writeAndFlush("hello 我是客户端，初次连接，多多照顾\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("服务器发来的消息："+msg+"\r\n");
//        ctx.writeAndFlush("我是客户端回复的消息\r\n");
    }
}
