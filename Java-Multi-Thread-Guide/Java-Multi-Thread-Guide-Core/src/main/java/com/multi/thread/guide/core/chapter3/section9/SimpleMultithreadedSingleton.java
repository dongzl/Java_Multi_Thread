package com.multi.thread.guide.core.chapter3.section9;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午11:12
 */
public class SimpleMultithreadedSingleton {

    private static SimpleMultithreadedSingleton instance = null;

    private SimpleMultithreadedSingleton() {

    }

    public static SimpleMultithreadedSingleton getInstance() {
        synchronized (SimpleMultithreadedSingleton.class) {
            if (null == instance) {
                instance = new SimpleMultithreadedSingleton();
            }
        }
        return instance;
    }

    public void someService() {
        
    }
}
