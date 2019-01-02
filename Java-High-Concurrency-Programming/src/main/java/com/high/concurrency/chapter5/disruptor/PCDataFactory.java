package com.high.concurrency.chapter5.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/02 下午4:34
 */
public class PCDataFactory implements EventFactory<PCData> {

    public PCData newInstance() {
        return new PCData();
    }
}
