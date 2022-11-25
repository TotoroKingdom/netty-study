package com.totoro.test2.demo07_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        Listener listener = new ListenerImpl();
        ListenerSupport support = new ListenerSupport();
        support.addListener(listener);

        support.triggerEvent(new Event("data","type"));
    }
}
