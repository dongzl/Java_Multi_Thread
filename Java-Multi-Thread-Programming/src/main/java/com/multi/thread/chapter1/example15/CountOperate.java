package com.multi.thread.chapter1.example15;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/05 下午4:13
 */
public class CountOperate extends Thread {

    public CountOperate() {
        System.out.println("CountOperate----begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println(this.getClass().getName());
        System.out.println("CountOperate----end");
    }

    @Override
    public void run() {
        System.out.println("run-----begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println(this.getClass().getName());
        System.out.println("run-----end");
    }
}
