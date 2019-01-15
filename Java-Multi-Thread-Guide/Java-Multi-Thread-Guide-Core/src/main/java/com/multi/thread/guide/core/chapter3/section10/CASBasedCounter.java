package com.multi.thread.guide.core.chapter3.section10;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 下午12:02
 */
public class CASBasedCounter {

    private volatile long count;

    public long value() {
        return count;
    }

    public void increment() {
        long oldValue;
        long newValue;
        do {
            oldValue = count;
            newValue = count + 1;
        } while (!compareAndSwap(oldValue, newValue));
    }

    private boolean compareAndSwap(long oldValue, long newValue) {
        //TODO
        return true;
    }
}
