package com.totoro.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2022/9/21
 * @description:
 */
@RestController
public class BioController {

    @SneakyThrows
    @RequestMapping("client")
    public String client(){
        System.out.println(Thread.currentThread().getName());
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("127.0.0.1",9090));
        System.out.println("waiting....");
        return null;
    }

    @SneakyThrows
    @RequestMapping("server")
    public String server(){
        System.out.println(Thread.currentThread().getName());

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(9090));

        List<SocketChannel> channels = new ArrayList<>();
        while (true){
            SocketChannel sc= ssc.accept();
            if (null != sc){
                System.out.println("连接成功="+sc);
                sc.configureBlocking(false);
                channels.add(sc);
            }

            for (SocketChannel channel : channels) {
                System.out.println("读取数据："+channel);
                int read = channel.read(buffer);
                if (read > 0){
                    buffer.flip();
                    buffer.clear();
                    System.out.println("读完之后："+channel);
                }
            }
        }

    }
}
