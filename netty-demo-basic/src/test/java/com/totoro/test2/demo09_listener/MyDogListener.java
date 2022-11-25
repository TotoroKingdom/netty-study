package com.totoro.test2.demo09_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class MyDogListener implements DogListener {
    @Override
    public void payListener(Event event) {
        System.out.println(event.getObject().getClass().getSimpleName() + "is happy");
    }

    @Override
    public void studyListener(Event event) {
        System.out.println(event.getObject().getClass().getSimpleName() + "is study");
    }
}
