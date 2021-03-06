package com.multi.thread.chapter3.section1.example6;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/14 下午10:48
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        a.start();
        NotifyThread notifyThread = new NotifyThread(lock);
        notifyThread.start();
        SyncNotifyMethodThread s = new SyncNotifyMethodThread(lock);
        s.start();
    }
}
