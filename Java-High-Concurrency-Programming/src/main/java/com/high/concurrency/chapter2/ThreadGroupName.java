package com.high.concurrency.chapter2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 上午10:17
 */
public class ThreadGroupName implements Runnable {

    public static void main(String[] args) throws Exception {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");
        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }

    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName() + "-" + Thread.currentThread().getName();
        while (true) {
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
