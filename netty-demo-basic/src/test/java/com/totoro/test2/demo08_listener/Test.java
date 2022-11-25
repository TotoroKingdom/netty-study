package com.totoro.test2.demo08_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class Test {

    public static void main(String[] args) {

        Listen listen = new Listen();
        Bus bus = new Bus();
        bus.setName("成华大道");
        Source source = new Source();
        source.notice(listen, bus);

    }
}
