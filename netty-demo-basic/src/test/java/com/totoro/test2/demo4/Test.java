package com.totoro.test2.demo4;

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
        //创建一个委托容器
        DelegateContainer container = new DelegateContainer();
        //将学生和老师加入委托容器
        container.attach(ming,"eat");
        container.attach(wang,"eat");
        container.attach(laowang,"barbecue");
        System.out.println("开始通知学生和老师开饭.....................");
        container.sNotify();

        System.out.println("小王回家去了，晚餐不用通知他了................");
        container.detach(wang,"eat");
        container.sNotify();

    }
}
