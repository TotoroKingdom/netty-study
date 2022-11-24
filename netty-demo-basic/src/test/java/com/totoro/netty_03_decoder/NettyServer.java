package com.totoro.netty_03_decoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author:totoro
 * @createDate:2022/11/24
 * @description:
 */
public class NettyServer {
    public static void main(String[] args) {
        new NettyServer().bind(10003);
    }

    private void bind(int port){
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer());

            ChannelFuture future = b.bind(port).sync();
            System.out.println("netty server start done");

            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}
