package com.totoro.test2.demo;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        WxUser wxUser1 = new WxUser("小明");
        WxUser wxUser2 = new WxUser("小红");
        WxUser wxUser3 = new WxUser("小C");

        SubjectImpl subject = new SubjectImpl();
        subject.attach(wxUser1);
        subject.attach(wxUser2);
        subject.attach(wxUser3);

        subject.notify("你好");
    }
}
