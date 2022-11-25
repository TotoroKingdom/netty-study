package com.totoro.test2.demo08_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class Source {
    public void notice(Listen listen, Bus bus){
        listen.driving(bus);
    }
}
