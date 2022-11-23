package com.totoro.test2.demo;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class WxUser implements Observer{

    private String name;

    public WxUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + ":" + message);
    }
}
