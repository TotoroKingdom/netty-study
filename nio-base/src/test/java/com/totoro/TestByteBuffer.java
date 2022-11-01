package com.totoro;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {

    public static void main(String[] args) {
        String url = "C:\\Users\\totoro\\IdeaProjects\\netty-study\\nio-base\\src\\main\\resources\\data1";


        try (FileChannel channel = new FileInputStream(url).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true){
                int read = channel.read(buffer);
                if (read == -1){
                    break;
                }
                System.out.println("====");
                buffer.flip();

                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    System.out.println((char) b);
                }

                buffer.clear();

            }


        } catch (IOException e) {
        }

    }
}
