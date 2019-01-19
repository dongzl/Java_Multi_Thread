package com.multi.thread.guide.core.chapter5.section3;

import com.multi.thread.guide.core.Tools;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/19 下午9:53
 */
public class CountDownLatchExample {

    private static final CountDownLatch latch = new CountDownLatch(4);

    private static int data;

    public static void main(String[] args) throws Exception {
        Thread workThread = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    data = i;
                    latch.countDown();
                    Tools.randomPause(1000);
                }
            };
        };
        workThread.start();
        latch.await();
        System.out.println("It's done. data = " + data);
    }
}
