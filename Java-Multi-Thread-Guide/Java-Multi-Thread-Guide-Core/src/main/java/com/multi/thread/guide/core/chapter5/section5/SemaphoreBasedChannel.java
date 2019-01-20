package com.multi.thread.guide.core.chapter5.section5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/20 下午9:42
 */
public class SemaphoreBasedChannel<P> implements Channel<P> {

    private final BlockingQueue<P> queue;

    private final Semaphore semaphore;

    public SemaphoreBasedChannel(BlockingQueue<P> queue, int flowLimit) {
        this(queue, flowLimit, false);
    }

    public SemaphoreBasedChannel(BlockingQueue<P> queue, int flowLimit, boolean isFair) {
        this.queue = queue;
        this.semaphore = new Semaphore(flowLimit, isFair);
    }

    @Override
    public void put(P product) throws InterruptedException {
        semaphore.acquire();
        try {
            queue.put(product);
        } finally {
            semaphore.release();
        }
    }

    @Override
    public P take() throws InterruptedException {
        return queue.take();
    }
}
