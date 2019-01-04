package com.high.concurrency.chapter6.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午6:42
 */

@FunctionalInterface
public interface IntHandler {

    void handle(int i);
}
