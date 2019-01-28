package com.multi.thread.guide.core.chapter8;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/28 上午9:29
 */
public class XThreadFactory implements ThreadFactory {

    private final Thread.UncaughtExceptionHandler ueh;

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;

    public XThreadFactory(Thread.UncaughtExceptionHandler ueh, String namePrefix) {
        this.ueh = ueh;
        this.namePrefix = namePrefix;
    }

    public XThreadFactory() {
        this(new LoggingUncaughtExceptionHandler(), "thread");
    }

    protected Thread doMakeThread(final Runnable r) {
        return new Thread(r) {
            @Override
            public String toString() {
                ThreadGroup group = getThreadGroup();
                String groupName = null == group ? "" : group.getName();
                String threadInfo = getClass().getSimpleName() + "[" + getName() + "," + getId() + "," + groupName + "]@" + hashCode();
                return threadInfo;
            }
        };
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = doMakeThread(r);
        t.setUncaughtExceptionHandler(ueh);
        t.setName(namePrefix + "-" + threadNumber.getAndIncrement());
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

    static class LoggingUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(e);
        }
    }
}
