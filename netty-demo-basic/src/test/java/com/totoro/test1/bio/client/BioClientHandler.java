package com.totoro.test1.bio.client;

import com.totoro.test1.bio.ChannelAdapter;
import com.totoro.test1.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class BioClientHandler extends ChannelAdapter {

    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("连接成功！服务器地址=" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("你好，我是客户端");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {

        System.out.println("服务器发来的消息：" + msg);

        ctx.writeAndFlush("客户端已经收到消息\r\n");

    }
}
