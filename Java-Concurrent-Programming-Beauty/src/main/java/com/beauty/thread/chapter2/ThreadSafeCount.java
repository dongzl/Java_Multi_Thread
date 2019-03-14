package com.beauty.thread.chapter2;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-14 09:41
 */
public class ThreadSafeCount {

    private long value;

    public synchronized long getCount() {
        return value;
    }

    public synchronized void inc() {
        value++;
    }
}
