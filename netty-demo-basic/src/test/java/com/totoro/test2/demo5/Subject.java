package com.totoro.test2.demo5;

import com.totoro.test2.demo5.Observer;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description: 抽象的发布对象
 */
public interface Subject {
    //订阅
    public void attach(Observer observer);

    //取消订阅
    public void detach(Observer observer);

    //消息发布
    public void sNotify();
}
