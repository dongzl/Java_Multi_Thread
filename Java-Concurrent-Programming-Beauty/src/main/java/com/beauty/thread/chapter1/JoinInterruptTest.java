package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-10 15:28
 */
public class JoinInterruptTest {

    public static void main(String args[]) throws Exception {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread one begin run!");
                for (;;){

                }
            }
        });

        final Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mainThread.interrupt();
            }
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
