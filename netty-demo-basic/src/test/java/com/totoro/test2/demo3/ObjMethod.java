package com.totoro.test2.demo3;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class ObjMethod {

    private Object obj;
    private String method;

    public ObjMethod(Object obj, String method) {
        this.obj = obj;
        this.method = method;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    @Override
    public int hashCode() {
        return this.getObj().hashCode() * this.getMethod().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        ObjMethod m = (ObjMethod) obj;
        boolean b = this.getObj() == m.getObj() && this.getMethod().equals(m.getMethod());
        return b;
    }
}
