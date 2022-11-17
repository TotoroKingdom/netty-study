package com.totoro.test1.anoymous;

/**
 * @author:totoro
 * @createDate:2022/11/17
 * @description:
 */
public class TestAnonymous {
    public static void main(String[] args) {

        Test test = new Test();
        TestA a = new TestAImpl(test);

        a.eat("123",1);

    }

}
