package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-11 17:29
 */
public class ThreadLocalTest2 {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    
    public static void main(String args[]) throws Exception {
        threadLocal.set("hello world");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:" + threadLocal.get());
            }
        });
        thread.start();

        System.out.println("main:" + threadLocal.get());
    }
}
