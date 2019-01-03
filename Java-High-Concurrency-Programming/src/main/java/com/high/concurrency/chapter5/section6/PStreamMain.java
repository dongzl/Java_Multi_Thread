package com.high.concurrency.chapter5.section6;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/03 下午12:53
 */
public class PStreamMain {

    public static void main(String[] args) throws Exception {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();
        for (int i = 0; i <= 1000; i++) {
            for (int j = 1; j <= 1000; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgStr = "((" + i + " + " + j + ") * " + i + ")/2";
                Plus.bq.add(msg);
            }
        }
    }
}
