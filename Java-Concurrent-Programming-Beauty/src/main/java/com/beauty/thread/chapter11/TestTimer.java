package com.beauty.thread.chapter11;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-10 09:59
 */
public class TestTimer {

    static Timer timer = new Timer();

    public static void main(String args[]) throws Exception {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("----one task----");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("error");
            }
        }, 500);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("----two task----");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
}
