package com.totoro.test2.demo04_delegate;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Teacher {

    //教师姓名
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    /**
     * 老师吃烧烤
     */
    public void barbecue(){
        System.out.println(name+"老师去烧烤摊吃烧烤");
    }
}
