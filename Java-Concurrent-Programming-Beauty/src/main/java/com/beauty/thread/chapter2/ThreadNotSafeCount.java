package com.beauty.thread.chapter2;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-14 09:41
 */
public class ThreadNotSafeCount {

    private long value;

    public long getCount() {
        return value;
    }

    public void inc() {
        value++;
    }
}
