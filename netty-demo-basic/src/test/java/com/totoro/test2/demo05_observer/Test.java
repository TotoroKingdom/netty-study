package com.totoro.test2.demo05_observer;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Test {

    public static void main(String[] args) {

        Student ming = new Student("小明");
        Student wang = new Student("小王");
        Teacher laowang = new Teacher("老王");

        Chef chef = new Chef();
        //订阅消息
        chef.attach(ming);
        chef.attach(wang);
        chef.attach(laowang);
        //发布消息
        chef.sNotify();
    }
}
