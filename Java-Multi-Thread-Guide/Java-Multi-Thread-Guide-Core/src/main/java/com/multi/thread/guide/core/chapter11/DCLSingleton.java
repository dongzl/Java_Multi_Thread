package com.multi.thread.guide.core.chapter11;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-12 17:22
 */
public class DCLSingleton {

    private static volatile DCLSingleton instance;

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
