package com.multi.thread.guide.core.chapter3.section9;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午11:12
 */
public class DCLSingleton {

    private static volatile DCLSingleton instance = null;

    private DCLSingleton() {

    }

    public static DCLSingleton getInstance() {
        if (null == instance) {
            synchronized (DCLSingleton.class) {
                if (null == instance) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

    public void someService() {
        
    }
}
