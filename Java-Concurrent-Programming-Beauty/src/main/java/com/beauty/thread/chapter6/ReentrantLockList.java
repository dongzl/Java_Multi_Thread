package com.beauty.thread.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-21 18:32
 */
public class ReentrantLockList {

    private List<String> list = new ArrayList<>();

    private volatile ReentrantLock lock = new ReentrantLock();

    public void add(String e) {
        lock.lock();
        try {
            list.add(e);
        } finally {
            lock.unlock();
        }
    }

    public void remove(String e) {
        lock.lock();
        try {
            list.remove(e);
        } finally {
            lock.unlock();
        }
    }

    public String get(int index) {
        lock.lock();
        try {
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }
}
