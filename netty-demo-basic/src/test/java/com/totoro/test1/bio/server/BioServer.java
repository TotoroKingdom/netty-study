package com.totoro.test1.bio.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class BioServer extends Thread{

    private ServerSocket serverSocket = null;

    public static void main(String[] args) {
        new BioServer().start();
    }


    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(10089));
            System.out.println("bio server start done");
            while (true){
                Socket accept = serverSocket.accept();
                BioServerHandler handler = new BioServerHandler(accept, Charset.forName("GBK"));
                handler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
