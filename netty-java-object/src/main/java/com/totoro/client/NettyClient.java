package com.totoro.client;

import com.totoro.util.MsgUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author:totoro
 * @createDate:2022/12/16
 * @description:
 */
public class NettyClient {
    public static void main(String[] args) {
        new NettyClient().connect("127.0.0.1",10001);
    }

    private void connect(String inetHost, int inetPort){
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer());

            ChannelFuture f = b.connect(inetHost, inetPort).sync();

            System.out.println("client start done");

            f.channel().writeAndFlush(MsgUtil.buildMsg(f.channel().id().toString(),"hello this is client"));

            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}