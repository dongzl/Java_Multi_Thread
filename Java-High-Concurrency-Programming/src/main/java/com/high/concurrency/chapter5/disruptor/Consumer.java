package com.high.concurrency.chapter5.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/02 下午4:32
 */
public class Consumer implements WorkHandler<PCData> {

    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + ":Event:--" + event.get() * event.get() + "--");
    }
}
