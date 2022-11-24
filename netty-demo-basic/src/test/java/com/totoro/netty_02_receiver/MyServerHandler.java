package com.totoro.netty_02_receiver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/11/24
 * @description:
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        byte[] msgByte = new byte[buf.readableBytes()];
        buf.readBytes(msgByte);
        System.out.println(LocalDateTime.now() + "收到消息：" + new String(msgByte, Charset.forName("GBK")));

    }
}
