package com.totoro.netty_11_udp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class NettyClient {

    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new MyChannelInitializer());
            Channel channel = b.bind(10111).sync().channel();

            channel.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("你好，我是客户端", Charset.forName("GBK")),
                    new InetSocketAddress("127.0.0.1", 10011)
            )).sync();
            channel.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
