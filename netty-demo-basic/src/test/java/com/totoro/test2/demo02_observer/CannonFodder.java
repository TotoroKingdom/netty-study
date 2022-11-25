package com.totoro.test2.demo02_observer;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class CannonFodder implements Observer{

    private int id;

    public CannonFodder(int id) {
        this.id = id;
    }

    public void getDown(){
        System.out.println(this.getClass().getSimpleName() + " id:" + id + " getDowned");
    }

    @Override
    public void update() {
        getDown();
    }
}
