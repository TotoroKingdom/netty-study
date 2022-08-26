package com.totoro.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author:totoro
 * @createDate:2022/8/26
 * @description:
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("decoder",new StringDecoder());
        socketChannel.pipeline().addLast("encoder",new StringEncoder());
        socketChannel.pipeline().addLast(new NettyClientHandler());
    }
}
