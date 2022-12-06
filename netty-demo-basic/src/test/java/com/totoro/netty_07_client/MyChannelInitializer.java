package com.totoro.netty_07_client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author:totoro
 * @createDate:2022/12/5
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        System.out.println("连接到客户端 remote: "+channel.remoteAddress());
        System.out.println("连接到客户端 id: "+channel.id());

    }
}
