package com.totoro.test1.aio.client;

import com.totoro.test1.aio.ChannelAdapter;
import com.totoro.test1.aio.ChannelHandler;

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

    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {

    }
}
