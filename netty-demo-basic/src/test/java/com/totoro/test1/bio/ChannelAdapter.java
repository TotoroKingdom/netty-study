package com.totoro.test1.bio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public abstract class ChannelAdapter extends Thread {

    private Socket socket;
    private ChannelHandler channelHandler;
    private Charset charset;

    public ChannelAdapter(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
        while (!socket.isConnected()){
            break;
        }
        channelHandler = new ChannelHandler(this.socket, charset);
        channelActive(channelHandler);
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), charset));
            String str = null;

            while ((str = input.readLine()) != null){
                channelRead(channelHandler, str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void channelActive(ChannelHandler ctx);

    public abstract void channelRead(ChannelHandler ctx, Object msg);
}
