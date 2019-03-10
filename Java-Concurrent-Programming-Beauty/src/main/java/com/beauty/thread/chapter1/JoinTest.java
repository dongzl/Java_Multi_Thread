package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-10 15:23
 */
public class JoinTest {

    public static void main(String args[]) throws Exception {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("child thread one over");
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("child thread two over");
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("all child thread over");
    }
}
