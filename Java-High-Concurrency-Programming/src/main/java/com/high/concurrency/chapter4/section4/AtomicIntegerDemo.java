package com.high.concurrency.chapter4.section4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/29 下午4:50
 */
public class AtomicIntegerDemo {

    //static AtomicInteger i = new AtomicInteger();

    static int i = 0;

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                //i.incrementAndGet();
                i++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];
        for (int k = 0; k < 10; k++) {
            threads[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            threads[k].start();
        }
        for (int k = 0; k < 10; k++) {
            threads[k].join();
        }
        System.out.println(i);
    }
}
