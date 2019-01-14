package com.multi.thread.guide.core.chapter3.section4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/14 上午10:01
 */
public class ReadWriteLockUsage {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public void reader() {
        readLock.lock();
        try {

        } finally {
            readLock.unlock();
        }
    }

    public void writeLock() {
        writeLock.lock();
        try {

        } finally {
            writeLock.unlock();
        }
    }
}
