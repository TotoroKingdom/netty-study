package com.totoro.test1.aio;

import java.util.concurrent.*;

/**
 * @author:totoro
 * @createDate:2022/11/17
 * @description:
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            try {
                System.out.println("小睡一会");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "我是字符串");
        System.out.println(future.get());

        executor.shutdown();


    }
}
