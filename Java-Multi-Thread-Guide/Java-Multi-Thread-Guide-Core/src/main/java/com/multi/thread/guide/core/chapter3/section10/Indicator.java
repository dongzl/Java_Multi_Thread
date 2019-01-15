package com.multi.thread.guide.core.chapter3.section10;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 下午1:50
 */
public class Indicator {

    private static final Indicator INSTANCE = new Indicator();

    private final AtomicLong requestCount = new AtomicLong(0);

    private final AtomicLong successCount = new AtomicLong(0);

    private final AtomicLong failureCount = new AtomicLong(0);

    private Indicator() {

    }

    public static Indicator getInstance() {
        return INSTANCE;
    }

    public void newRequestReceived() {
        requestCount.incrementAndGet();
    }

    public void newRequestProcessed() {
        successCount.incrementAndGet();
    }

    public void requestProcessedFailed() {
        failureCount.incrementAndGet();
    }

    public long getRequestCount() {
        return requestCount.get();
    }

    public long getSuccessCount() {
        return successCount.get();
    }

    public long getFailureCount() {
        return failureCount.get();
    }

    public void reset() {
        requestCount.set(0);
        successCount.set(0);
        failureCount.set(0);
    }
}
