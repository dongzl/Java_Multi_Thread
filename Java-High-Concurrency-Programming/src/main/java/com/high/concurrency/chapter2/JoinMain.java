package com.high.concurrency.chapter2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 上午9:41
 */
public class JoinMain {

    public volatile static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 10000000; i++);
        }
    }

    public static void main(String[] args) throws Exception {
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
