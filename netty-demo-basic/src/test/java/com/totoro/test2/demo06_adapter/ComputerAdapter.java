package com.totoro.test2.demo06_adapter;

import java.util.Map;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description: 类适配器
 */
public class ComputerAdapter extends Xiaomi implements Computer{
    @Override
    public void getName() {
        Map<String, Object> map = super.getMsg();
        System.out.println(map.get("name"));
    }

    @Override
    public void getPrice() {
        Map<String, Object> map = super.getMsg();
        System.out.println(map.get("price"));
    }

}
