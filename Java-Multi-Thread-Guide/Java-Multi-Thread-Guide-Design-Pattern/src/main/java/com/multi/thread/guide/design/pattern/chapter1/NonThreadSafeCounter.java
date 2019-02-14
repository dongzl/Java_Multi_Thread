package com.multi.thread.guide.design.pattern.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-14 15:21
 */
public class NonThreadSafeCounter {

    private int counter = 0;

    public void increment() {
        counter++;
    }

    public int get() {
        return counter;
    }
}
