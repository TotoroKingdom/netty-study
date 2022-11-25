package com.totoro.test2.demo03_delegate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class ReflectTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        A a1 = new A();

        Class<?> aClass = a1.getClass();

        Method a = aClass.getMethod("a");
        a.invoke(a1);


    }


}
