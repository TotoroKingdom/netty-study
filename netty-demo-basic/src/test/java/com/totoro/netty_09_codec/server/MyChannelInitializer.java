package com.totoro.netty_09_codec.server;

import com.totoro.netty_09_codec.MyDecoder;
import com.totoro.netty_09_codec.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new MyDecoder());

        channel.pipeline().addLast(new MyEncoder());

        channel.pipeline().addLast(new MyServerHandler());
    }
}
