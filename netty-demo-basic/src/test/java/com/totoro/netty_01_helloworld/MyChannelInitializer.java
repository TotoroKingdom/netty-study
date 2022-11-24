package com.totoro.netty_01_helloworld;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;


/**
 * @author:totoro
 * @createDate:2022/11/24
 * @description:
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        InetSocketAddress address = channel.localAddress();

        System.out.println("有客户端成功连接到服务器：" + address.getAddress());
        System.out.println("hostname：" + address.getHostName());
        System.out.println("hoststring：" + address.getHostString());
        System.out.println("port：" + address.getPort());
    }
}
