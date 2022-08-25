package com.totoro.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.totoro.serialize.Serializer;

/**
 * @author:totoro
 * @createDate:2022/8/25
 * @description:
 */
public class JSONSerializer implements Serializer {

    public byte[] serialize(Object o) {
        return JSON.toJSONBytes(o);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }

}
