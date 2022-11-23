package com.totoro.test2.demo3;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        S s = new S();
        s.attach(new A(),"a");
        s.attach(new A(), "a");

        B b = new B();
        s.attach(b,"b");
        s.sNotify();

        System.out.println("Step 1!");

        s.detach(b, "b");
        s.sNotify();


    }
}
