package com.totoro.server;

import com.totoro.codec.ObjDecoder;
import com.totoro.codec.ObjEncoder;
import com.totoro.domain.FileTransferProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new MyServerHandler());
    }
}
