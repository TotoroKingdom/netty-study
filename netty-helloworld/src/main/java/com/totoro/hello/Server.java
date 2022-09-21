package com.totoro.hello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.ByteBuffer;

/**
 * @author:totoro
 * @createDate:2022/9/21
 * @description:
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {

        //1-启动器，组装组装netty组件
        ServerBootstrap boot = new ServerBootstrap();
                boot
                .group(new NioEventLoopGroup(1),new NioEventLoopGroup(2))//2-加入nio组的监听器（里面包含selector选择器）
                .channel(NioServerSocketChannel.class)//3-选择服务器的ServerSocketChannel实现
                .childHandler(//4-boss负责连接处理  worker（child）负责处理读写，决定了worker能执行那些操作（handler）
                        new ChannelInitializer<NioSocketChannel>() {//5-channel代表和客户端进行数据读写通道initializer初始化，负责添加其他的handler


                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {//建立连接后执行
                                //6-添加具体的handler
//                                ch.pipeline().addLast(new StringDecoder());//将ByteBuf转换为字符串
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {//自定义handler
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf byteBuf = msg instanceof ByteBuf ? ((ByteBuf) msg) : null;
                                        if (byteBuf != null){
                                            byte[] buf = new byte[16];
                                            ByteBuf len = byteBuf.readBytes(buf, 0, byteBuf.readableBytes());
                                            System.out.println(new String(buf));
                                        }
                                        super.channelRead(ctx, msg);
                                    }
                                });

                            }
                        }
                ).bind(9090).sync();



    }
}
