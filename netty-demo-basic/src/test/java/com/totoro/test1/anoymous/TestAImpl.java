package com.totoro.test1.anoymous;

/**
 * @author:totoro
 * @createDate:2022/11/17
 * @description:
 */
public class TestAImpl implements TestA<String, Integer> {

    private Test test;

    public TestAImpl(Test test) {
        this.test = test;
    }

    @Override
    public void eat(String s, Integer integer) {
        test.read(new TestA<String, Integer>() {
            @Override
            public void eat(String s, Integer integer) {
                System.out.println("哈哈哈，我喜欢套娃"+s+integer);
            }

            @Override
            public void sleep(Integer integer) {
                System.out.println("哈哈哈，套娃失败"+integer);
            }
        });
    }

    @Override
    public void sleep(Integer integer) {
        System.out.println("睡了"+integer+"小时");
    }
}
