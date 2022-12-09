package com.totoro.netty_11_udp.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class NettyServer {
    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.SO_RCVBUF, 2048*1024)//读缓冲区
                    .option(ChannelOption.SO_SNDBUF, 1024*1024)//写缓冲区
                    .handler(new MyChannelInitializer());

            ChannelFuture future = b.bind(10011).sync();
            System.out.println("udp server start done");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }
}
