package com.high.concurrency.chapter5;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/01 下午8:52
 */
public class StaticSingleton {

    private StaticSingleton() {
        System.out.println();
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
