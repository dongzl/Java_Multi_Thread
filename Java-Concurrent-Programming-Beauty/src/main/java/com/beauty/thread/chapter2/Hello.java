package com.beauty.thread.chapter2;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-15 10:37
 */
public class Hello {

    public synchronized void helloA() {
        System.out.println("hello");
    }

    public synchronized void helloB() {
        System.out.println("hello B");
        helloA();
    }
}
