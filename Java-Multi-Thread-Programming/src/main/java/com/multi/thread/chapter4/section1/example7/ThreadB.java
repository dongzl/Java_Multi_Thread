package com.multi.thread.chapter4.section1.example7;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/19 下午10:03
 */
public class ThreadB extends Thread {

    private MyService service;

    public ThreadB(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.awaitB();
    }
}
