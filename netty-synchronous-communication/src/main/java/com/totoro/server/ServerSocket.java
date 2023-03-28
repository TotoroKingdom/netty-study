package com.totoro.server;

import com.totoro.codec.RpcDecoder;
import com.totoro.codec.RpcEncoder;
import com.totoro.msg.Request;
import com.totoro.msg.Response;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author:totoro
 * @createDate:2023/3/27
 * @description:
 */
public class ServerSocket implements Runnable {

    private ChannelFuture f;

    @Override
    public void run() {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(
                                    new RpcDecoder(Request.class),
                                    new RpcEncoder(Response.class),
                                    new MyServerHandler()
                            );
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
