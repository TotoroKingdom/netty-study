package com.totoro.test1.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class NioClient {
    public static void main(String[] args) throws IOException {

        Selector open = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        boolean isConnect = socketChannel.connect(new InetSocketAddress("localhost", 10087));
        if (isConnect){
            socketChannel.register(open, SelectionKey.OP_READ);
        }else {
            socketChannel.register(open, SelectionKey.OP_CONNECT);
        }
        System.out.println("nio client start done");

        new NioClientHandler(open, Charset.forName("GBK")).start();

    }
}
