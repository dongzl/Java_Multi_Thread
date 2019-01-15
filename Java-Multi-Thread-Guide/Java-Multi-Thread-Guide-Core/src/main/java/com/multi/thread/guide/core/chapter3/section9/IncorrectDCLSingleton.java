package com.multi.thread.guide.core.chapter3.section9;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午11:12
 */
public class IncorrectDCLSingleton {

    private static IncorrectDCLSingleton instance = null;

    private IncorrectDCLSingleton() {

    }

    public static IncorrectDCLSingleton getInstance() {
        if (null == instance) {
            synchronized (IncorrectDCLSingleton.class) {
                if (null == instance) {
                    instance = new IncorrectDCLSingleton();
                }
            }
        }
        return instance;
    }

    public void someService() {
        
    }
}
