package com.thread;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-01 20:56
 */
public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread:%s\n", t.getName());
        System.out.printf("Exception: %s: %s:\n", e.getClass().getName(), e.getMessage());
        System.out.printf("Stack Trace:\n");
        e.printStackTrace();
        System.out.printf("Thread status:%s\n", t.getState());
    }
}
