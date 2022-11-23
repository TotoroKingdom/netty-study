package com.totoro.test2.demo3;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class S {
    private HashSet<ObjMethod> list = new HashSet<>();

    public void attach(Object obj, String method){
        list.add(new ObjMethod(obj,method));
    }

    public void detach(Object obj, String method){
        list.remove(new ObjMethod(obj, method));
    }

    public void sNotify(){
        if (list.isEmpty()){
            return;
        }

        Iterator<ObjMethod> iterator = list.iterator();
        while (iterator.hasNext()){
            ObjMethod m = (ObjMethod) iterator.next();

            Class<?> objClass = m.getObj().getClass();

            try {
                Method method = objClass.getMethod(m.getMethod());
                method.invoke(m.getObj());

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
