package com.high.concurrency.chapter3.section1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 下午3:10
 */
public class FairLock implements Runnable {

    public static ReentrantLock fairLock = new ReentrantLock(true);

    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getId() + ":获得锁");
            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FairLock lock1 = new FairLock();
        Thread t1 = new Thread(lock1, "Thread_t1");
        Thread t2 = new Thread(lock1, "Thread_t2");
        t1.start();
        t2.start();

    }
}
