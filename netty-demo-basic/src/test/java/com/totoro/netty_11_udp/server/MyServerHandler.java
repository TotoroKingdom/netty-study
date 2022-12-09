package com.totoro.netty_11_udp.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author:totoro
 * @createDate:2022/12/9
 * @description:
 */
public class MyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {

        String msg = packet.content().toString(Charset.forName("GBK"));
        System.out.println(LocalDateTime.now() + "udp服务器：" + msg);

        String res = "服务端已经收到消息\r\n";
        byte[] bytes = res.getBytes(Charset.forName("GBK"));
        DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), packet.sender());
        ctx.writeAndFlush(data);
    }
}
