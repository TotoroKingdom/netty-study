package com.totoro.test2.demo5;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description: 具体的观察者
 */
public class Student implements Observer{

    //学生名字
    private String name;

    public Student(String name) {
        this.name = name;
    }

    /**
     * 学生去食堂开饭
     */
    public void eat(){
        System.out.println(name+"快去食堂开饭");
    }

}
