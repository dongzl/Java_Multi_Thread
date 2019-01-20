package com.multi.thread.guide.core.chapter5.section3;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/20 下午3:48
 */
public abstract class AbstractService implements Service {

    protected boolean started = false;

    protected final CountDownLatch latch;

    public AbstractService(CountDownLatch latch) {
        this.latch = latch;
    }

    protected abstract void doStart() throws Exception;

    @Override
    public void start() {
        new ServiceStart().start();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return started;
    }

    class ServiceStart extends Thread {
        @Override
        public void run() {
            final String serviceName = AbstractService.class.getClass().getSimpleName();
            System.out.println("Starting " + serviceName);
            try {
                doStart();
                started = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
                System.out.println("Done starting " + serviceName);
            }
        }
    }
}
