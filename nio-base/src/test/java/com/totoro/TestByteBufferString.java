package com.totoro;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TestByteBufferString {
    public static void main(String[] args) {

        String s = "hello world";

        //1-字符串转为ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put(s.getBytes());

        //2-encode
        ByteBuffer charsetBuffer = StandardCharsets.UTF_8.encode(s);
        for (int i = 0; i < charsetBuffer.capacity() - 1; i++) {
            System.out.println( (char) charsetBuffer.get(i));
        }

        //3-wrap
        ByteBuffer wrapBuffer = ByteBuffer.wrap(s.getBytes());
        for (int i = 0; i < wrapBuffer.capacity(); i++) {
            System.out.println( (char) wrapBuffer.get(i));
        }

        //4-decode
        String s1 = StandardCharsets.UTF_8.decode(charsetBuffer).toString();
        String s2 = StandardCharsets.UTF_8.decode(wrapBuffer).toString();
        System.out.println(s1+"-"+s2);

    }
}
