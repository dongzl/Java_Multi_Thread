package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-11 16:28
 */
public class DaemonTest {

    public static void main1(String args[]) throws Exception {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    public static void main(String args[]) throws Exception {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {}
            }
        });

//        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
