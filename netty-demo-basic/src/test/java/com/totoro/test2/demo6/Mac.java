package com.totoro.test2.demo6;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Mac implements Computer {
    @Override
    public void getName() {
        System.out.println("苹果电脑");
    }

    @Override
    public void getPrice() {
        System.out.println("18888RMB");
    }
}
