package com.totoro.test2.demo05_observer;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description: 具体的观察者
 */
public class Teacher implements Observer{

    //教师姓名
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    /**
     * 老师吃烧烤
     */
    public void eat(){
        System.out.println(name+"老师去烧烤摊吃烧烤");
    }
}
