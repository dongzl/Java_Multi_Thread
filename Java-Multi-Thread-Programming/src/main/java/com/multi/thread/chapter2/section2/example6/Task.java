package com.multi.thread.chapter2.section2.example6;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/10 下午7:33
 */
public class Task {

    public synchronized void otherMethod() {
        System.out.println("----------------------run------------otherMethod");
    }

    public synchronized void doLongTimeTask() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("synchronized threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
        }
    }
}
