package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-11 14:30
 */
public class DeadLockTest {

    private static Object resourceA = new Object();

    private static Object resourceB = new Object();

    public static void main(String args[]) throws Exception {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get resourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + " waiting get resourceB");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + " get resourceB");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get resourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + " waiting get resourceA");
                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread() + " get resourceA");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
