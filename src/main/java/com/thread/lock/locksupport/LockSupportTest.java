package com.thread.lock.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/02 下午9:52
 */
public class LockSupportTest {

    private static Thread mainThread;

    public static void main(String[] args) throws Exception {
        ThreadA ta = new ThreadA("ta");
        // 获取主线程
        mainThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " start ta");
        ta.start();
        System.out.println(Thread.currentThread().getName() + " block");
        // 主线程阻塞
        LockSupport.park(mainThread);
        System.out.println(Thread.currentThread().getName() + " continue");
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " wakeup others");
            // 唤醒“主线程”
            LockSupport.unpark(mainThread);
        }
    }
}
