package com.thread.lock.stampedlock;

import java.util.concurrent.locks.StampedLock;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/03 下午10:31
 */
public class Point {

    private double x, y;

    private final StampedLock sl = new StampedLock();

    void move(double deltax, double deltay) {
        long stamp = sl.writeLock();
        try {
             x += deltax;
             y += deltay;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        double currentx = x, currenty = y;
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                currentx = x;
                currenty = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentx * currentx + currenty * currenty);
    }

    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
}
