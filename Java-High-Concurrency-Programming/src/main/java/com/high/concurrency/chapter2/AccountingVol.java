package com.high.concurrency.chapter2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 上午11:39
 */
public class AccountingVol implements Runnable {

    static AccountingVol instance = new AccountingVol();

    static volatile int i = 0;

    public static void increase() {
        synchronized (instance) {
            i++;
        }
    }

    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
