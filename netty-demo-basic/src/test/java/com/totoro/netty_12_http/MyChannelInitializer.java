package com.totoro.netty_12_http;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new HttpResponseEncoder());
        channel.pipeline().addLast(new HttpResponseEncoder());
        channel.pipeline().addLast(new MyServerHandler());
    }
}
