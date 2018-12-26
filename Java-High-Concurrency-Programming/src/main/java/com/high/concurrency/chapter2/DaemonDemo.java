package com.high.concurrency.chapter2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 上午11:15
 */
public class DaemonDemo {

    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new DaemonT();
        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);

    }
}
