package com.multi.thread.guide.core.chapter1.section3;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/10 上午11:59
 */
public class WelcomeApp1 {

    public static void main(String[] args) throws Exception {
        Thread welcomeThread = new Thread(new WelcomeThread());
        welcomeThread.start();
        System.out.printf("1.Welcome! I'm %s.%n", Thread.currentThread().getName());
    }

    static class WelcomeThread implements Runnable {
        @Override
        public void run() {
            System.out.printf("2.Welcome! I'm %s.%n", Thread.currentThread().getName());
        }
    }
}
