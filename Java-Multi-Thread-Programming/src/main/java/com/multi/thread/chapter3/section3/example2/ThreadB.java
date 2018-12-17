package com.multi.thread.chapter3.section3.example2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/17 下午6:23
 */
public class ThreadB extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Tools.tl.set("ThreadB " + (i + 1));
                System.out.println("ThreadB get value = " + Tools.tl.get());
                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
