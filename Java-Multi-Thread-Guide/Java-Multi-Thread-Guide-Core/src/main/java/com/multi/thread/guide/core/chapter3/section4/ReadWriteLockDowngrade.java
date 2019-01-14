package com.multi.thread.guide.core.chapter3.section4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/14 上午10:05
 */
public class ReadWriteLockDowngrade {

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private final Lock writeLock = rwLock.writeLock();

    private final Lock readLock = rwLock.readLock();

    public void operationWithLockDowngrade() {
        boolean readLockAcquired = false;
        writeLock.lock();
        try {
            readLock.lock();
            readLockAcquired = true;
        } finally {
            writeLock.unlock();
        }
        if (readLockAcquired) {
            try {

            } finally {
                readLock.unlock();
            }
        } else {
            
        }
    }
}
