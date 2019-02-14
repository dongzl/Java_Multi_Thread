package com.multi.thread.guide.design.pattern.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-14 15:21
 */
public class ThreadSafeCounter {

    private int counter = 0;

    public void increment() {
        synchronized (this) {
            counter++;
        }
    }

    public int get() {
        synchronized (this) {
            return counter;
        }
    }
}
