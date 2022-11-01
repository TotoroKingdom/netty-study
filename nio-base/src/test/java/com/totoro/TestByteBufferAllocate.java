package com.totoro;

import java.nio.ByteBuffer;

public class TestByteBufferAllocate {
    public static void main(String[] args) {


        ByteBuffer allocate = ByteBuffer.allocate(16);
        ByteBuffer buffer = ByteBuffer.allocateDirect(16);

        System.out.println(allocate.getClass());
        System.out.println(buffer.getClass());
        System.out.println(buffer.position());
        System.out.println(buffer.mark());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
    }
}
