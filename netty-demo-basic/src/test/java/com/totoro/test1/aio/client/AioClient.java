package com.totoro.test1.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author:totoro
 * @createDate:2022/11/17
 * @description:
 */
public class AioClient {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> future = socketChannel.connect(new InetSocketAddress("localhost", 8001));
        System.out.println("client start done");
        future.get();
//        socketChannel.read(ByteBuffer.allocate(1024), null, new AioClientHandler(socketChannel, Charset.forName("GBK")));
        Thread.sleep(10000);

    }

}
