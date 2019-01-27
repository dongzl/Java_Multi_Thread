package com.multi.thread.guide.core.chapter7.section3;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/27 下午4:43
 */
public class DeadlockDetector extends Thread {

    static final ThreadMXBean tmb = ManagementFactory.getThreadMXBean();

    private final long monitorInterval;

    public DeadlockDetector(long monitorInterval) {
        super("DeadlockDetector");
        setDaemon(true);
        this.monitorInterval = monitorInterval;
    }

    public DeadlockDetector() {
        this(2000);
    }

    public static ThreadInfo[] findDeadlockedThreads() {
        long[] ids = tmb.findDeadlockedThreads();
        return null == tmb.findDeadlockedThreads() ? new ThreadInfo[0] : tmb.getThreadInfo(ids);
    }

    public static Thread findThreadById(long threadId) {
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (thread.getId() == threadId) {
                return thread;
            }
        }
        return null;
    }

    public static boolean interruptThread(long threadID) {
        Thread thread = findThreadById(threadID);
        if (null != thread) {
            thread.interrupt();
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        ThreadInfo[] threadInfoList;
        ThreadInfo ti;
        int i = 0;
        try {
            for (;;) {
                threadInfoList = DeadlockDetector.findDeadlockedThreads();
                if (threadInfoList.length > 0) {
                    ti = threadInfoList[i++ % threadInfoList.length];
                    System.out.println();
                    DeadlockDetector.interruptThread(ti.getThreadId());
                    continue;
                } else {
                    System.out.println();
                    i = 0;
                }
                Thread.sleep(monitorInterval);
            }
        } catch (Exception e) {

        }
    }
}
