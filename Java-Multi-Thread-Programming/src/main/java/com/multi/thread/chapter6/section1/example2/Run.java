package com.multi.thread.chapter6.section1.example2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/23 上午10:47
 */
public class Run {

    public static void main(String[] args) throws Exception {
        MyThread t1 = new MyThread();
        t1.start();
    }
}
