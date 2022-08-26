package com.totoro.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @author:totoro
 * @createDate:2022/8/26
 * @description:
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("decoder",new StringDecoder(StandardCharsets.UTF_8));
        socketChannel.pipeline().addLast("encoder",new StringEncoder(StandardCharsets.UTF_8));
        socketChannel.pipeline().addLast(new NettyServerHandler());
    }
}
