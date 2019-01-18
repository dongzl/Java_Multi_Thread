package com.multi.thread.guide.core.chapter5;

import com.multi.thread.guide.core.Tools;

import java.util.Random;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/18 上午10:17
 */
public class TimeoutWaitExample {

    private static final Object lock = new Object();

    private static boolean ready = false;

    protected static final Random random = new Random();

    public static void main(String[] args) throws Exception {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    synchronized (lock) {
                        ready = random.nextInt(100) < 20 ? true : false;
                        if (ready) {
                            lock.notify();
                        }
                    }
                    Tools.randomPause(500);
                }
            }
        };
        t.setDaemon(true);
        t.start();
        waiter(1000);
    }

    private static void waiter(int timeout) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException();
        }
        long start = System.currentTimeMillis();
        long waitTime;
        long now;
        synchronized (lock) {
            while (!ready) {
                now = System.currentTimeMillis();
                waitTime = timeout - (now - start);
                System.out.println("Remaining time to wait:" + waitTime);
                if (waitTime <= 0) {
                    break;
                }
                lock.wait(timeout);
            }
            if (ready) {
                guardedAction();
            } else {
                System.out.println("wait time out，unable to execution target action!");
            }
        }
    }

    private static void guardedAction() {
        System.out.println("Take some action...");
    }
}
