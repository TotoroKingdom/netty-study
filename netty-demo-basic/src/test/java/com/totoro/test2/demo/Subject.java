package com.totoro.test2.demo;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public interface Subject {

    public void attach(Observer observer);

    public void detach(Observer observer);

    public void notify(String message);
}
