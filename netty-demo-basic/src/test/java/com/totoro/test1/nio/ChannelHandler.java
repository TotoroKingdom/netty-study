package com.totoro.test1.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class ChannelHandler {

    private SocketChannel channel;

    private Charset charset;

    public ChannelHandler(SocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg){
        OutputStream out = null;
        try {
            byte[] bytes = msg.toString().getBytes(charset);
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketChannel  channel(){
        return channel;
    }
}
