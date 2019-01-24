package com.multi.thread.guide.core.chapter6.section4;

import java.util.HashMap;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/24 下午2:30
 */
public abstract class XAbstractTask implements Runnable {

    static ThreadLocal<HashMap<String, String>> configHolder = new ThreadLocal<HashMap<String, String>>() {

        @Override
        protected HashMap<String, String> initialValue() {
            return new HashMap<>();
        }
    };

    protected void preRun() {
        configHolder.get().clear();
    }

    protected void postRun() {

    }

    protected abstract void doRun();

    @Override
    public void run() {
        try {
            preRun();
            doRun();
        } finally {
            postRun();
        }
    }
}
