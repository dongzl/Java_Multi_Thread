package com.multi.thread.chapter2.section2.example26;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/12 下午12:39
 */
public class ThreadA extends Thread {

    private MyService service;

    public ThreadA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.testMethod();
    }
}
