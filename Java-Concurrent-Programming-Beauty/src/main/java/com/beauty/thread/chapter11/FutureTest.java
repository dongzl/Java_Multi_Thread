package com.beauty.thread.chapter11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-11 09:48
 */
public class FutureTest {

    //private final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.DiscardPolicy());

    private final static ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String args[]) throws Exception {
        Future futureOne = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runable one");
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Future futureTwo = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runable two");
            }
        });

        Future futureThree = null;
        try {
            futureThree = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start runable three");
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("task one " + futureOne.get());
        System.out.println("task two " + futureTwo.get());
        System.out.println("task three " + (futureThree == null ? null : futureThree.get()));

        executorService.shutdown();
    }
}
