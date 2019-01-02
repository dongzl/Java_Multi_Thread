package com.high.concurrency.chapter5.disruptor;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/01 下午9:17
 */
public final class PCData {

    private long value;

    public long get() {
        return value;
    }

    public void set(long value) {
        this.value = value;
    }
}
