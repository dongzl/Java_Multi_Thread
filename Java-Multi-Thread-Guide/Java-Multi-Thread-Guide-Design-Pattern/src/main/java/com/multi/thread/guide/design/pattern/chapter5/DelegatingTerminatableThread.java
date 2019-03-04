package com.multi.thread.guide.design.pattern.chapter5;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-04 16:30
 */
public class DelegatingTerminatableThread extends AbstractTerminatableThread {
    private final Runnable task;

    public DelegatingTerminatableThread(Runnable task) {
        this.task = task;
    }

    @Override
    protected void doRun() throws Exception {
        this.task.run();
    }

    public static AbstractTerminatableThread of(Runnable task) {
        DelegatingTerminatableThread ret = new DelegatingTerminatableThread(
                task);
        return ret;
    }
}
