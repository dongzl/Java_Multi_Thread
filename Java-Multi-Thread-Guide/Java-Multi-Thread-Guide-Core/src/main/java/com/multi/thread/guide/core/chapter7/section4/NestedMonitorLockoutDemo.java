package com.multi.thread.guide.core.chapter7.section4;

import com.multi.thread.guide.core.Tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/27 下午5:20
 */
public class NestedMonitorLockoutDemo {

    private final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

    private int processed = 0;

    private int accepted = 0;

    public static void main(String[] args) throws Exception {
        NestedMonitorLockoutDemo u = new NestedMonitorLockoutDemo();
        u.start();
        int i = 0;
        while (i-- < 100000) {
            u.accept("message" + i);
            Tools.randomPause(100);
        }
    }

    private void start() {
        new WorkThread().start();
    }

    private synchronized void accept(String message) throws InterruptedException {
        queue.put(message);
        accepted++;
    }

    private synchronized void doProcess() throws InterruptedException {
        String msg = queue.take();
        System.out.println("Process:" + msg);
        processed++;
    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    doProcess();
                }
            } catch (Exception e) {

            }
        }
    }


}
