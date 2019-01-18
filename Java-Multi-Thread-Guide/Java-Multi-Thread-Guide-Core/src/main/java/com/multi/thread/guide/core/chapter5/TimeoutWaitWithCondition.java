package com.multi.thread.guide.core.chapter5;

import com.multi.thread.guide.core.Tools;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/18 下午6:01
 */
public class TimeoutWaitWithCondition {

    private static final Lock lock = new ReentrantLock();

    private static final Condition condtion = lock.newCondition();

    private static boolean ready = false;

    protected static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    lock.lock();
                    try {
                        ready = random.nextInt(100) < 5 ? true : false;
                        if (ready) {
                            condtion.signal();
                        }
                    } finally {
                        lock.unlock();
                    }
                    Tools.randomPause(500);
                }
            }
        };
        t.setDaemon(true);
        t.start();
        waiter(1000);
    }

    private static void waiter(final long timeout) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException();
        }
        final Date dateline = new Date(System.currentTimeMillis() + timeout);
        boolean continueToWait = true;
        lock.lock();
        try {
            while (!ready) {
                System.out.println("still not ready, continue to wait:" + continueToWait);
                if (!continueToWait) {
                    System.out.println("Wait time out, unable to execution target action!");
                    return;
                }
                continueToWait = condtion.awaitUntil(dateline);
            }
        } finally {
            lock.unlock();
        }
    }
}
