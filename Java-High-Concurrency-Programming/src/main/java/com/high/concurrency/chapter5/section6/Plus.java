package com.high.concurrency.chapter5.section6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/03 上午9:36
 */
public class Plus implements Runnable {

    public static BlockingQueue<Msg> bq = new LinkedBlockingQueue<>();


    @Override
    public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.i = msg.i + msg.j;
                Multiply.bq.add(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
