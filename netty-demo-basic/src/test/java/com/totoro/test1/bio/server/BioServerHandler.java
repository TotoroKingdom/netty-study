package com.totoro.test1.bio.server;

import com.totoro.test1.bio.ChannelAdapter;
import com.totoro.test1.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class BioServerHandler extends ChannelAdapter {

    public BioServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("连接成功，客户端地址="+ctx.socket().getLocalAddress());

        ctx.writeAndFlush("这里是服务器，恭喜你连接成功\r\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {

        System.out.println(LocalDateTime.now()+"客户端发来的消息==" + msg);

        ctx.writeAndFlush("服务器已经成功收到消息\r\n");

    }
}
