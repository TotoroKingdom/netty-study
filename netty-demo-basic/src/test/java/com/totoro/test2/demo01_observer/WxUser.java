package com.totoro.test2.demo01_observer;

import lombok.Data;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
@Data
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
