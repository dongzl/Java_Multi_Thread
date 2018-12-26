package com.high.concurrency.chapter3.section1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 下午4:59
 */
public class SemapDemo implements Runnable {

    final Semaphore semp = new Semaphore(5);

    public void run() {
        try {
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":done!");
            semp.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exce = Executors.newFixedThreadPool(20);
        final SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exce.submit(semapDemo);
        }
    }
}
