package com.high.concurrency.chapter6.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午6:44
 */
public interface IHouse {

    void eat();

    default void run() {
        System.out.println("house run");
    }
}
