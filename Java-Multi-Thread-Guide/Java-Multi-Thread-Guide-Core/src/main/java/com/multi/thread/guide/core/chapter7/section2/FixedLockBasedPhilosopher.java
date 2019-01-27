package com.multi.thread.guide.core.chapter7.section2;

import com.multi.thread.guide.core.chapter7.section1.BuggyLckBasedPhilosopher;
import com.multi.thread.guide.core.chapter7.section1.Chopstick;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/27 下午4:09
 */
public class FixedLockBasedPhilosopher extends BuggyLckBasedPhilosopher {

    @Override
    protected boolean pickUpChopstick(Chopstick chopstick) {
        final ReentrantLock lock = LOCK_MAP.get(chopstick);
        boolean pickedUp = false;
        boolean lockAcquired = false;
        try {
            lockAcquired = lock.tryLock(50, TimeUnit.MILLISECONDS);
            if (!lockAcquired) {
                System.out.println();
                return false;
            }
        } catch (Exception e) {
            Chopstick theOtherChopstick = chopstick == left ? right : left;
            if (LOCK_MAP.get(theOtherChopstick).isHeldByCurrentThread()) {
                theOtherChopstick.putDown();
                LOCK_MAP.get(theOtherChopstick).unlock();
            }
            return false;
        }
        try {
            System.out.println();
            chopstick.pickUp();
            pickedUp = true;
        } catch (Exception e) {
            if (lockAcquired) {
                lock.unlock();
            }
            pickedUp = false;
            e.printStackTrace();
        }
        return pickedUp;
    }


    public FixedLockBasedPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }
}
