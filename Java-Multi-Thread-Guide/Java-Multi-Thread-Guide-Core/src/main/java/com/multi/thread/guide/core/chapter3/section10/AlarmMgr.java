package com.multi.thread.guide.core.chapter3.section10;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 下午1:59
 */
public enum AlarmMgr implements Runnable {

    INSTANCE;

    private final AtomicBoolean initializating = new AtomicBoolean(false);

    AlarmMgr() {

    }

    public void init() {
        if (initializating.compareAndSet(false, true)) {
            new Thread(this).start();
        }
    }

    public int sendAlarm(String message) {
        int result = 0;
        return result;
    }


    @Override
    public void run() {

    }
}
