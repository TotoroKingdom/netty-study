package com.totoro.test1.anoymous;

/**
 * @author:totoro
 * @createDate:2022/11/17
 * @description:
 */
public class TestAnonymous {
    public static void main(String[] args) {

//        Test test = new Test();
//        TestA a = new TestAImpl(test);
//
//        a.eat("123",1);

        double my = 0;
        double total = 0;

        for (int i = 0; ; i++) {
            System.out.println(i+1+"月份");

            if (i == 0){
                my = 10000;
                total = 10000;
            }else {

                double temp = total;
                System.out.println("percent: "+my/total);
                total = total + 10000;
                double add = (my/temp) * 8000;
                my = my + add;

                System.out.println("my: "+my);
                System.out.println("total: "+total);

            }
            if ( (my/total) < 0.5){
                break;
            }
        }

    }

}
