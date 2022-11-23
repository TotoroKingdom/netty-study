package com.totoro.test2.demo5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description: 具体的发布者
 */
public class Chef implements Subject{

    private List<Observer> list = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        list.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void sNotify() {
        if (list.isEmpty()) {
            return;
        }

        for (Observer observer : list) {
            observer.eat();
        }
    }
}
