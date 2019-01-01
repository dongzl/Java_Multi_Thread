package com.high.concurrency.chapter5;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/01 下午8:49
 */
public class LazySingleton {

    private LazySingleton() {
        System.out.println("LazySingleton is create");
    }

    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
