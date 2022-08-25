package com.totoro.serialize;

/**
 * @author:totoro
 * @createDate:2022/8/25
 * @description:
 */
public interface Serializer {

    byte[] serialize(Object o);

    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
