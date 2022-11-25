package com.totoro.test2.demo04_delegate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description: 委托容器
 */
public class DelegateContainer {

    //定义map,用来存放注册要通知的对象和对象需要执行的方法 Object = 对象， String = 方法名
    private HashMap<Object,String> map = new HashMap<>();

    //注册要通知的对象和方法
    public void attach(Object object, String method){
        map.put(object,method);
    }

    //删除通知的对象
    public void detach(Object object, String method){
        map.remove(object);
    }

    //通过反射执行注册的对象和其对应的方法
    public void sNotify() {
        if (map.isEmpty()){
            return;
        }
        //遍历map
        for (Map.Entry<Object, String> entry : map.entrySet()) {
            String method = entry.getValue();
            Object object = entry.getKey();
            //通过反射获取对象,如果接收的object是Student的实例对象，那么aClass就等于Student
            Class<?> aClass = object.getClass();
            try {
                //执行object实例的method方法
                Method m = aClass.getMethod(method);
                m.invoke(object);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
