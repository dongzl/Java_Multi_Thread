package com.beauty.thread.chapter10;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-26 10:17
 */
public class CycleBarrierTest1 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " task1 mege result");
        }
    });

    public static void main(String args[]) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + " task1-1");
                    System.out.println(Thread.currentThread() + "enter in barrier");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " enter out barrier");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + " task1-2");
                    System.out.println(Thread.currentThread() + "enter in barrier");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " enter out barrier");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();


    }
}
