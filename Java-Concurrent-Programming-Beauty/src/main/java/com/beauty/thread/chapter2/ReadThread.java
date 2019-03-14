package com.beauty.thread.chapter2;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-14 20:48
 */
public class ReadThread extends Thread {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (ready) {//(1)
                System.out.println(num + num);//(2)
            }
            System.out.println("read thread...");
        }
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;//(3)
            ready = true;//(4)
            System.out.println("WriteThread set over...");
        }
    }

    private static int num = 0;
    private static boolean ready = false;

    public static void main(String args[]) throws Exception {
        ReadThread rt = new ReadThread();
        rt.start();

        WriteThread wt = new WriteThread();
        wt.start();

        Thread.sleep(10);

        rt.interrupt();
        System.out.println("main exit");
    }
}
