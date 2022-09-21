package com.totoro.hello;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutor;

/**
 * @author:totoro
 * @createDate:2022/9/21
 * @description:
 */
public class EventLoopGroupTest {
    public static void main(String[] args) {

        DefaultEventExecutorGroup group = new DefaultEventExecutorGroup(2);
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        for (EventExecutor even : group) {
            System.out.println(even);
        }
    }
}
