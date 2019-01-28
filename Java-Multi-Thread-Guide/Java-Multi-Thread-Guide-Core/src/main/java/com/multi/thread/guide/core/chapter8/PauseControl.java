package com.multi.thread.guide.core.chapter8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/28 上午9:43
 */
public class PauseControl extends ReentrantLock {

    private volatile boolean suspended = false;

    private final Condition condSuspended = newCondition();

    public void requestPause() {
        suspended = true;
    }

    public void proceed() {
        lock();
        try {
            suspended = false;
            condSuspended.signalAll();
        } finally {
            unlock();
        }
    }

    public void pauseIfNecessary(Runnable targetAction) throws InterruptedException {
        lock();
        try {
            while (suspended) {
                condSuspended.await();
            }
            targetAction.run();
        } finally {
            unlock();
        }
    }
}
