package com.totoro.test2.demo06_adapter;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description: 对象适配器
 */
public class ComputerAdapter2 implements Computer{

    private Phone phone;

    public ComputerAdapter2(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void getName() {
        System.out.println(phone.getMsg().get("name"));
    }

    @Override
    public void getPrice() {
        System.out.println(phone.getMsg().get("price"));
    }

}
