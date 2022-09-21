package com.totoro;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class NioBaseApplicationTests {

    @Test
    void fileTest() throws IOException {
        Path path = Paths.get("C:\\Users\\totoro\\IdeaProjects\\netty-study\\nio-base\\src\\main\\resources");
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println(dir);
                dirCount.incrementAndGet();
                if (dir.toFile().getName().equals("data")){
                    Files.delete(dir);
                }
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                if (file.toFile().getName().equals("data1.txt")){
                    Files.delete(file);
                }
                return super.visitFile(file, attrs);
            }

        });

        System.out.println("目录="+dirCount);
        System.out.println("文件="+fileCount);

    }


    @Test
    void contextLoads() throws FileNotFoundException {
        try(FileChannel channel = new FileInputStream("C:\\Users\\totoro\\IdeaProjects\\netty-study\\nio-base\\src\\main\\resources\\data.txt")
                            .getChannel()){
            ByteBuffer buffer  = ByteBuffer.allocate(20);
            while (true){
                int len = channel.read(buffer);
                System.out.println("读取到的字节数："+len);
                if (len == -1){
                    break;
                }

                //切换到读模式
                buffer.flip();
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    System.out.println("实际字节："+ (char) b);
                }
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
