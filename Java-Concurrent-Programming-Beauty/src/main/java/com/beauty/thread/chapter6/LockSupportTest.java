package com.beauty.thread.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-18 10:14
 */
public class LockSupportTest {

    public static void main(String args[]) throws Exception {
        System.out.println("begin park");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("end park");
    }
}
