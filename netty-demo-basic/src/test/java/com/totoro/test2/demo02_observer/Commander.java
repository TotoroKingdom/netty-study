package com.totoro.test2.demo02_observer;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Commander implements Subject {

    private int targetPlaceID;
    private HashSet<Observer> gunnerSet = new HashSet<>();

    @Override
    public void attach(Observer observer) {
        gunnerSet.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        gunnerSet.remove(observer);
    }

    @Override
    public void sNotify() {
        if (gunnerSet.isEmpty()){
            return;
        }

        Iterator<Observer> iterator = gunnerSet.iterator();
        while (iterator.hasNext()){
            Observer next = iterator.next();
            next.update();
        }
    }

    @Override
    public int getState() {
        return targetPlaceID;
    }

    @Override
    public void setState(int state) {
        targetPlaceID = state;
    }
}
