package com.beauty.thread.chapter10;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-26 10:17
 */
public class CycleBarrierTest2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String args[]) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + " step1");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " step2");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " step3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + " step1");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " step2");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " step3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();


    }
}
