package com.multi.thread.chapter2.section2.example19;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/11 下午6:32
 */
public class ThreadB extends Thread {

    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.methodB();
    }
}
