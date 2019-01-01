package com.high.concurrency.chapter5.pc;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/01 下午9:17
 */
public final class PCData {

    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
