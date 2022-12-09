package com.totoro.netty_11_udp.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class MyClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket packet) throws Exception {
        String msg = packet.content().toString(Charset.forName("GBK"));
        System.out.println(LocalDateTime.now() + "UDP客户端收到消息："+msg);
    }
}
