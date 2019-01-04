package com.high.concurrency.chapter6.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午6:48
 */
public interface IDonkey {

    void eat();

    default void run() {
        System.out.println("donkey run");
    }
}
