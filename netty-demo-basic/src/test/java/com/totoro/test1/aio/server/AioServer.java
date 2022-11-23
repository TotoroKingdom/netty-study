package com.totoro.test1.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class AioServer extends Thread{

    private AsynchronousServerSocketChannel serverSocketChannel;


    @Override
    public void run() {
        try{
            serverSocketChannel = AsynchronousServerSocketChannel.open(AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10));

            serverSocketChannel.bind(new InetSocketAddress(10086));
            System.out.println("aio server start done");
            CountDownLatch latch = new CountDownLatch(1);
            serverSocketChannel.accept(this, new AioServerChannelInitializer()  );
            latch.await();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel serverSocketChannel(){
        return serverSocketChannel;
    }

    public static void main(String[] args){
        System.out.println(Thread.currentThread());
        AioServer aioServer = new AioServer();
        aioServer.start();
    }
}
