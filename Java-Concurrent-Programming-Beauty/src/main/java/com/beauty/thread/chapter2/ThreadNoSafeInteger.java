package com.beauty.thread.chapter2;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-14 09:36
 */
public class ThreadNoSafeInteger {

    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}
