package com.totoro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author:totoro
 * @createDate:2022/9/21
 * @description:
 */
@RestController
@RequestMapping("nio")
public class NioController {

    @RequestMapping("client")
    public String client() throws IOException {

        System.out.println(Thread.currentThread().getName());

        try (Socket socket = new Socket("localhost", 9090)){
            System.out.println(socket);
            socket.getOutputStream().write("world".getBytes(StandardCharsets.UTF_8));
            System.in.read();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("server")
    public String server(){

        try (ServerSocketChannel channel = ServerSocketChannel.open()){
            channel.bind(new InetSocketAddress(9090));
            System.out.println(channel);

            Selector selector = Selector.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (true){
                int count = selector.select();
                System.out.println("select count:" + count);

                Set<SelectionKey> keys = selector.selectedKeys();

                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()){
                        ServerSocketChannel c = (ServerSocketChannel) key.channel();

                        SocketChannel sc = c.accept();
                        System.out.println(sc);
                    }
                    iter.remove();
                }


            }


        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
