package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-09 08:45
 */
public class RunnableTask implements Runnable {

    @Override
    public void run() {
        System.out.println("I am a child thread");
    }

    public static void main(String args[]) throws Exception {
        RunnableTask task = new RunnableTask();
        new Thread(task).start();
        new Thread(task).start();
    }
}
