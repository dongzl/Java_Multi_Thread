package com.multi.thread.guide.core.chapter3.section4;

import com.multi.thread.guide.core.chapter2.section2.CircularSeqGenerator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/13 下午9:35
 */
public class LockbasedCircularSeqGenerator implements CircularSeqGenerator {

    private short sequence = -1;

    private final Lock lock = new ReentrantLock();

    @Override
    public short nextSequence() {
        lock.lock();
        try {
            if (sequence >= 999) {
                sequence = 0;
            } else {
                sequence++;
            }
        } finally {
            lock.unlock();
        }
        return 0;
    }
}
