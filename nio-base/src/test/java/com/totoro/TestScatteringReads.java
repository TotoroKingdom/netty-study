package com.totoro;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestScatteringReads {
    public static void main(String[] args) {

        String url = "C:\\Users\\totoro\\IdeaProjects\\netty-study\\nio-base\\src\\main\\resources\\3part.txt";
        String url1 = "C:\\Users\\totoro\\IdeaProjects\\netty-study\\nio-base\\src\\main\\resources\\readpart.txt";
        String url2 = "C:\\Users\\totoro\\IdeaProjects\\netty-study\\nio-base\\src\\main\\resources\\readpart1.txt";
        String u = "C:\\Users\\totoro\\IdeaProjects\\netty-study\\nio-base\\target\\classes\\readpart.txt";
        try (FileChannel channel = new RandomAccessFile(url, "r").getChannel()) {
            ByteBuffer buffer1 = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(3);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);
            ByteBuffer[] byteBuffers = {buffer1, buffer2, buffer3};
            channel.read(byteBuffers);
            buffer1.flip();
            buffer2.flip();
            buffer3.flip();

            FileChannel rw = new RandomAccessFile(url1, "rw").getChannel();
            rw.write(byteBuffers);


        } catch (IOException e) {
        }


    }
}
