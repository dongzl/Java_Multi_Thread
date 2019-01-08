package com.high.concurrency.chapter7.section13;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/08 下午12:02
 */
public class GBestMsg {

    final PosValue value;

    public GBestMsg(PosValue value) {
        this.value = value;
    }

    public PosValue getValue() {
        return value;
    }
}
