package com.totoro.test2.demo06_adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Xiaomi implements Phone {
    @Override
    public Map<String, Object> getMsg() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","小米手机");
        map.put("price","1666RMB");
        return map;
    }
}
