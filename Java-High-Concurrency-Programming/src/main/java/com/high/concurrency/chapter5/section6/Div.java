package com.high.concurrency.chapter5.section6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/03 下午12:50
 */
public class Div implements Runnable {

    public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.i = msg.i / 2;
                System.out.println(msg.orgStr + " = " + msg.i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
