package com.multi.thread.guide.core.chapter3.section4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/13 下午9:44
 */
public class ExplicitLockInfo {

    private static final Lock lock = new ReentrantLock();

    private static int shardData = 0;

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    try {
                        Thread.sleep(2200000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        t.start();
        Thread.sleep(100);
        lock.lock();
        try {
            lock.lock();
            System.out.println("shardData:" + shardData);
        } finally {
            lock.unlock();
        }
    }
}
