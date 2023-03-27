package com.totoro.client;

import com.totoro.codec.RpcDecoder;
import com.totoro.codec.RpcEncoder;
import com.totoro.msg.Request;
import com.totoro.msg.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author:totoro
 * @createDate:2023/3/27
 * @description:
 */
public class ClientSocket implements Runnable {

    private ChannelFuture future;

    @Override
    public void run() {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(
                            new RpcDecoder(Response.class),
                            new RpcEncoder(Request.class),
                            new MyClientHandler());
                }
            });

            ChannelFuture f = b.connect("127.0.0.1", 7379).sync();
            this.future = f;
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public ChannelFuture getFuture(){
        return future;
    }

    public void setFuture(ChannelFuture future){
        this.future = future;
    }
}
