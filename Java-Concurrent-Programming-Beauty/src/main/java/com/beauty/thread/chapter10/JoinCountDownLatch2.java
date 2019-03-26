package com.beauty.thread.chapter10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-26 09:58
 */
public class JoinCountDownLatch2 {

    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String args[]) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new Runnable() {
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

        executorService.submit(new Runnable() {
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

        System.out.println("wait all child thread over!");

        countDownLatch.await();

        System.out.println("all child thread over!");

        executorService.shutdown();
    }
}
