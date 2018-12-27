package com.high.concurrency.chapter3.section2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/27 下午2:03
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleWithFixedDelay(new Runnable() {
//        ses.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
//                    Thread.sleep(1000);
                    Thread.sleep(8000);
                    System.out.println(System.currentTimeMillis() / 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
