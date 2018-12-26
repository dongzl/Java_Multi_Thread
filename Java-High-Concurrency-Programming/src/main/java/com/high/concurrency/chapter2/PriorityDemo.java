package com.high.concurrency.chapter2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 上午11:24
 */
public class PriorityDemo {

    public static class HighPriority extends Thread {

        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (HighPriority.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("HighPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {

        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (LowPriority.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread high = new HighPriority();
        Thread low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}
