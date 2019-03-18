package com.beauty.thread.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-18 10:14
 */
public class LockSupportTest2 {

    public static void main(String args[]) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park");
                LockSupport.park();
                System.out.println("child thread unpark");
            }
        });

        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark");
        LockSupport.unpark(thread);
    }
}
