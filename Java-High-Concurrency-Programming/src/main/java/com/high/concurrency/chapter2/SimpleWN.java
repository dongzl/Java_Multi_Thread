package com.high.concurrency.chapter2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/25 下午8:11
 */
public class SimpleWN {

    final static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start！");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object");
                    object.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start！notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
