package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-10 15:49
 */
public class YieldTest implements Runnable {

    public YieldTest() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread() + " Yield CPU...");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + "is over");
    }

    public static void main(String args[]) throws Exception {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}
