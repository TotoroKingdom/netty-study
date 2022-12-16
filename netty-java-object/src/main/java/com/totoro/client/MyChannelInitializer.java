package com.totoro.client;

import com.totoro.codec.ObjDecoder;
import com.totoro.codec.ObjEncoder;
import com.totoro.domain.MsgInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ObjectDecoder;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new ObjDecoder(MsgInfo.class));
        channel.pipeline().addLast(new ObjEncoder(MsgInfo.class));
        channel.pipeline().addLast(new MyClientHandler());
    }
}
