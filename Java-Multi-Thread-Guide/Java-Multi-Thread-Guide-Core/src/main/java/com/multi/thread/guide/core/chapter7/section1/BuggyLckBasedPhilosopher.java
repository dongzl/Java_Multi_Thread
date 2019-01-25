package com.multi.thread.guide.core.chapter7.section1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/25 下午4:17
 */
public class BuggyLckBasedPhilosopher extends AbstractPhilosopher {

    protected final static ConcurrentMap<Chopstick, ReentrantLock> LOCK_MAP;

    static {
        LOCK_MAP = new ConcurrentHashMap<>();
    }

    public BuggyLckBasedPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
        LOCK_MAP.putIfAbsent(left, new ReentrantLock());
        LOCK_MAP.putIfAbsent(right, new ReentrantLock());
    }

    @Override
    public void eat() {
        if (pickUpChopstick(left) && pickUpChopstick(right)) {
            try {
                doEat();
            } finally {
                putDownChopsticks(right, left);
            }
        }
    }

    protected boolean pickUpChopstick(Chopstick chopstick) {
        final ReentrantLock lock = LOCK_MAP.get(chopstick);
        lock.lock();
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

    private void putDownChopsticks(Chopstick chopstick1, Chopstick chopstick2) {
        try {
            putDownChopstick(chopstick1);
        } finally {
            putDownChopstick(chopstick2);
        }
    }

    private void putDownChopstick(Chopstick chopstick) {
        final ReentrantLock lock = LOCK_MAP.get(chopstick);
        try {
            System.out.println();
            chopstick.putDown();
        } finally {
            lock.unlock();
        }
    }
}
