package com.totoro.test2.demo08_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description: 监听器
 */
public class Listen {

    public void driving(Bus bus){
        if (bus.getName().equals("成华大道")){
            System.out.println("请过二仙桥");
        }
    }
}
