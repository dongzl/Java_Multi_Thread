package com.multi.thread.chapter1.example34;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/08 下午8:21
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 50000000; i++) {
            Thread.yield();
            count = count + (i + 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时：" + (endTime - beginTime) + "毫秒！");
    }
}
