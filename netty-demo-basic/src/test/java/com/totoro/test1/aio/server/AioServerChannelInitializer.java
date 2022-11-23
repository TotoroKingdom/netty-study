package com.totoro.test1.aio.server;

import com.totoro.test1.aio.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class AioServerChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(AsynchronousSocketChannel channel) throws Exception {
        channel.read(ByteBuffer.allocate(1024), 10, TimeUnit.SECONDS, null,
                new AioServerHandler(channel, Charset.forName("GBK")));
    }
}
