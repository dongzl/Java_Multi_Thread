package com.multi.thread.guide.core.chapter3.section8;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/14 下午8:06
 */
public class Counter {

    private volatile long count;

    public long value() {
        return count;
    }

    public void increment() {
        synchronized (this) {
            count++;
        }
    }
}
