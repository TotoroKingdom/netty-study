package com.totoro.test1.nio.server;

import com.totoro.test1.nio.ChannelAdapter;
import com.totoro.test1.nio.ChannelHandler;

import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class NioServerHandler extends ChannelAdapter {
    public NioServerHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("客户端地址"+ctx.channel().getLocalAddress());
            ctx.writeAndFlush("服务器已经成功连接 \r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {

        System.out.println(LocalDateTime.now() + "msg=" + msg);

        ctx.writeAndFlush("服务器已经成功收到信息\r\n");

    }
}
