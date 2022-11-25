package com.totoro.test2.demo09_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class Dog {

    private MyDogListener myDogListener;

    public void setDogListener(MyDogListener myDogListener){
        this.myDogListener = myDogListener;
    }

    public void play(){
        System.out.println("Dog: I am playing");
        Event event = new Event(this);
        myDogListener.payListener(event);
    }

    public void study(){
        System.out.println("Dog: I am studying");
        Event event = new Event(this);
        myDogListener.studyListener(event);
    }

}
