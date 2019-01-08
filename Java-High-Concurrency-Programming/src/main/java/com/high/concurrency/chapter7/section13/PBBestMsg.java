package com.high.concurrency.chapter7.section13;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/08 下午12:06
 */
public class PBBestMsg {

    final PosValue value;

    public PBBestMsg(PosValue value) {
        this.value = value;
    }

    public PosValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
