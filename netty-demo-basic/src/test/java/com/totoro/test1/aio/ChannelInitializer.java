package com.totoro.test1.aio;

import com.totoro.test1.aio.server.AioServer;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.spi.AbstractSelectableChannel;

/**
 * @author:totoro
 * @createDate:2022/11/17
 * @description:
 */
public abstract class ChannelInitializer<S extends AbstractSelectableChannel> implements CompletionHandler<AsynchronousSocketChannel, AioServer> {


    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer attachment) {
        try {
            initChannel(channel);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            attachment.serverSocketChannel().accept(attachment, this);
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.getStackTrace();
    }

    protected abstract void initChannel(AsynchronousSocketChannel channel) throws Exception;
}
