package com.totoro.test2.demo09_listener;

/**
 * @author:totoro
 * @createDate:2022/11/25
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        Dog dog = new Dog();
        MyDogListener myDogListener = new MyDogListener();
        dog.setDogListener(myDogListener);

        dog.play();
        dog.study();
    }
}
