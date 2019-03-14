package com.beauty.thread.chapter2;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-14 09:36
 */
public class ThreadSafeInteger {

    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
