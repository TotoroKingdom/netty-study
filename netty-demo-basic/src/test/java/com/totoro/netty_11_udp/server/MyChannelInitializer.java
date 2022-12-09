package com.totoro.netty_11_udp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<NioDatagramChannel> {

    private EventLoopGroup group = new NioEventLoopGroup();

    @Override
    protected void initChannel(NioDatagramChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(group, new MyServerHandler());
    }
}
