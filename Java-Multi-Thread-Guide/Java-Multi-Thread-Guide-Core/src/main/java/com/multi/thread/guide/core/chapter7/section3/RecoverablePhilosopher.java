package com.multi.thread.guide.core.chapter7.section3;

import com.multi.thread.guide.core.chapter7.section1.BuggyLckBasedPhilosopher;
import com.multi.thread.guide.core.chapter7.section1.Chopstick;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/27 下午4:56
 */
public class RecoverablePhilosopher extends BuggyLckBasedPhilosopher {

    public RecoverablePhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    protected boolean pickUpChopstick(Chopstick chopstick) {
        final ReentrantLock lock = LOCK_MAP.get(chopstick);
        try {
            lock.lockInterruptibly();
        } catch (Exception e) {
            System.out.println();
            Chopstick theOtherChopstick = chopstick == left ? right : left;
            theOtherChopstick.putDown();
            LOCK_MAP.get(theOtherChopstick).unlock();
            return false;
        }
        try {
            System.out.println();
            chopstick.pickUp();
        } catch (Exception e) {
            e.printStackTrace();
            lock.unlock();
            return false;
        }
        return true;
    }
}
