package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-10 16:13
 */
public class InterruptTest {

    public static void main1(String args[]) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + "hello");
                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread interrupt thread");
        thread.interrupt();
        thread.join();
        System.out.println("main is over");
    }

    public static void main2(String args[]) throws Exception {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("threadOne begin sleep for 2000 seconds");
                    Thread.sleep(2000000);
                    System.out.println("threadOne awaking");
                } catch (Exception e) {
                    System.out.println("threadOne is interrupted while sleeping");
                    return;
                }
                System.out.println("threadOne leaving normally");
            }
        });

        threadOne.start();
        Thread.sleep(1000);
        threadOne.interrupt();
        threadOne.join();
        System.out.println("main thread is over");
    }

    public static void main3(String args[]) throws Exception {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {}
            }
        });

        threadOne.start();
        threadOne.interrupt();
        System.out.println("isInterrupted:" + threadOne.isInterrupted());
        System.out.println("isInterrupted:" + threadOne.interrupted());
        System.out.println("isInterrupted:" + Thread.interrupted());
        System.out.println("isInterrupted:" + threadOne.isInterrupted());
        System.out.println("main thread is over");
    }

    public static void main(String args[]) throws Exception {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().interrupted()) {
                    
                }
                System.out.println("threadOne isInterrupted:" + Thread.currentThread().isInterrupted());
            }
        });

        threadOne.start();
        threadOne.interrupt();
        threadOne.join();
        System.out.println("main thread is over");
    }
}
