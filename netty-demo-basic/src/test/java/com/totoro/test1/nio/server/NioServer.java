package com.totoro.test1.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class NioServer {

    private Selector selector;

    private ServerSocketChannel socketChannel;

    public static void main(String[] args) {
        new NioServer().bind(10087);
    }

    public void bind(int port){
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(port), 1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("nio server start done");
            new NioServerHandler(selector, Charset.forName("GBK")).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
