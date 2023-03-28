package com.totoro.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2023/3/28
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        channel.pipeline().addLast(new IdleStateHandler(2,2,2));

        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));

        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));

        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));

        channel.pipeline().addLast(new MyServerHandler());

    }
}
