package com.multi.thread.guide.core.chapter5.section5;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/20 下午9:23
 */
public interface Channel<P> {

    void put(P product) throws InterruptedException;

    P take() throws InterruptedException;
    
}
