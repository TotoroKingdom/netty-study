package com.totoro.test2.demo6;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        Computer apple = new Mac();
        apple.getName();
        apple.getPrice();
        //类适配器
        Computer adapter = new ComputerAdapter();
        adapter.getName();
        adapter.getPrice();
        //对象适配器
        Computer adapter2 = new ComputerAdapter2(new Xiaomi());
        adapter.getName();
        adapter.getPrice();



    }
}
