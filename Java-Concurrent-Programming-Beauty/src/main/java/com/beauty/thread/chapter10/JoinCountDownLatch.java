package com.beauty.thread.chapter10;

import java.util.concurrent.CountDownLatch;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-26 09:58
 */
public class JoinCountDownLatch {

    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String args[]) throws Exception {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("child threadOne cover!");
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("child threadTwo cover!");
            }
        });

        threadOne.start();
        threadTwo.start();
        
        System.out.println("wait all child thread over!");

        countDownLatch.await();

        System.out.println("all child thread over!");
    }
}
