package com.thread.lock.isalive;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/05 下午7:43
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        MyThread myThread = new MyThread();
//        myThread.start();
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        t1.start();
    }
}
