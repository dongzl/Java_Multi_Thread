package com.multi.thread.guide.core.chapter3.section9;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午11:12
 */
public class SingleThreadSingleton {

    private static SingleThreadSingleton instance = null;

    private SingleThreadSingleton() {

    }

    public static SingleThreadSingleton getInstance() {
        if (null == instance) {
            instance = new SingleThreadSingleton();
        }
        return instance;
    }

    public void someService() {

    }
}
