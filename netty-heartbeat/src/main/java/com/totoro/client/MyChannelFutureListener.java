package com.totoro.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * @author:totoro
 * @createDate:2023/3/28
 * @description:
 */
public class MyChannelFutureListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {

        if (channelFuture.isSuccess()){
            System.out.println("netty client start done");
            return;
        }

        final EventLoop loop = channelFuture.channel().eventLoop();
        loop.schedule(new Runnable() {
            @Override
            public void run() {
                try{
                    new NettyClient().connect("127.0.0.1",7397);
                    System.out.println("监听器：netty client start  done..");
                    Thread.sleep(500);
                } catch (Exception e){
                    System.out.println("netty client start error go reconnect..");
                }
            }
        }, 1L, TimeUnit.SECONDS );
    }
}
