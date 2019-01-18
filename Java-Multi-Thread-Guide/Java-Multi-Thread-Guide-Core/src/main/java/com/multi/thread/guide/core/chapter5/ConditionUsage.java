package com.multi.thread.guide.core.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/18 下午5:10
 */
public class ConditionUsage {

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public void aGuardMethod() throws InterruptedException {
        lock.lock();
        try {
            while (checkCondtion()) {
                condition.await();
            }
            doAction();
        } finally {
            lock.unlock();
        }
    }

    private boolean checkCondtion() {
        //TODO
        return false;
    }

    private void doAction() {
        //
    }

    public void anNotificationMethod() throws InterruptedException {
        lock.lock();
        try {
            changeState();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    private void changeState() {
        // ...
    }
}
