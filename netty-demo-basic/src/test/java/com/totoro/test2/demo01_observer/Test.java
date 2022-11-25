package com.totoro.test2.demo01_observer;

/**
 * @author:totoro
 * @createDate:2022/11/23
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        WxUser wxUser1 = new WxUser("小明");
        WxUser wxUser2 = new WxUser("小明");
        WxUser wxUser3 = new WxUser("小C");

        SubjectImpl subject = new SubjectImpl();
        subject.attach(wxUser1);
        subject.attach(wxUser2);
        subject.attach(wxUser3);

        subject.notify("你好");
        System.out.println(wxUser1.toString());
        System.out.println(Integer.toHexString(wxUser1.hashCode()));
        System.out.println(wxUser2.toString());
        System.out.println(Integer.toHexString(wxUser2.hashCode()));
        System.out.println(wxUser1.equals(wxUser2));
        System.out.println(wxUser1 == wxUser2);
    }
}
