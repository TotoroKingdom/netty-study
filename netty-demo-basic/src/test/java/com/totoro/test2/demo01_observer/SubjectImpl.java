package com.totoro.test2.demo01_observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class SubjectImpl implements Subject {

    private List<Observer> list = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        list.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : list) {
            observer.update(message);
        }
    }
}
