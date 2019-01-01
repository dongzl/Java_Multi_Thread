package com.high.concurrency.chapter5;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/01 下午8:45
 */
public class Singleton {

    private Singleton() {
        System.out.println("Singleton is create");
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}
