package com.high.concurrency.chapter4.section4;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/29 下午5:45
 */
public class AtomicIntegerArrayDemo {

    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                array.getAndIncrement(k % array.length());
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
        System.out.println(array);
    }
}
