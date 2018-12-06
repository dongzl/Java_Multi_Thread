package com.thread.lock.isalive;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/05 下午7:41
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().isAlive());
        System.out.println(this.isAlive());
    }

    public static void main(String[] args) throws Exception {
//        MyThread myThread = new MyThread();
//        myThread.start();
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        t1.start();
    }
}
