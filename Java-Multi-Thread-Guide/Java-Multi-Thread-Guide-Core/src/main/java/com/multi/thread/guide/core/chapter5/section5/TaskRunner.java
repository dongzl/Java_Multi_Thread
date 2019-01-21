package com.multi.thread.guide.core.chapter5.section5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/21 上午11:27
 */
public class TaskRunner {

    protected final BlockingQueue<Runnable> channel;

    protected final Thread workThread;

    public TaskRunner(BlockingQueue<Runnable> channel) {
        this.channel = channel;
        this.workThread = new WorkThread();
    }

    public TaskRunner() {
        this(new LinkedBlockingQueue<>());
    }

    public void init() {
        final Thread t = workThread;
        if (null != t) {
            t.start();
        }
    }

    public void submit(Runnable task) throws InterruptedException {
        channel.put(task);
    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            Runnable task = null;
            try {
                for (;;) {
                    task = channel.take();
                    try {
                        task.run();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
