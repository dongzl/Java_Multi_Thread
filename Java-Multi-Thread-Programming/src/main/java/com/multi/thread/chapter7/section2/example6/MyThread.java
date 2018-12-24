package com.multi.thread.chapter7.section2.example6;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/24 下午12:02
 */
public class MyThread extends Thread {

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        System.out.println("ThreadName = " + Thread.currentThread().getName() + " 准备开始死循环了：）");
        while (!this.isInterrupted()) {

        }
        System.out.println("ThreadName = " + Thread.currentThread().getName() + " 结束了：）");
    }
}
