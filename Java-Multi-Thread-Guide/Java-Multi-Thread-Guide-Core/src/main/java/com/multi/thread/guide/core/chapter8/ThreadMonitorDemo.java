package com.multi.thread.guide.core.chapter8;

import com.multi.thread.guide.core.Tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/27 下午6:32
 */
public class ThreadMonitorDemo {

    volatile boolean inited = false;

    static int threadIndex = 0;

    final BlockingQueue<String> channel = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) throws Exception {
        ThreadMonitorDemo demo = new ThreadMonitorDemo();
        demo.init();
        for (int i = 0; i < 100; i++) {
            demo.service("test-" + i);
        }
        Thread.sleep(2000);
        System.exit(0);
    }

    private synchronized void init() {
        if (inited) {
            return;
        }
        System.out.println();
        WorkThread t = new WorkThread();
        t.setName("Worker0-" + threadIndex);
        t.setUncaughtExceptionHandler(new ThreadMonitor());
        t.start();
        inited = true;
    }

    private class ThreadMonitor implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
           String threadInfo = t.getName();
           inited = false;
           init();
        }
    }

    public void service(String message) throws InterruptedException {
        channel.put(message);
    }

    private class WorkThread extends Thread {
        @Override
        public void run() {
            String msg;
            try {
                for (;;) {
                    msg = channel.take();
                    process(msg);
                }
            } catch (Exception e) {

            }
        }

        private void process(String msg) {
            if ((int) (Math.random() * 100) < 2) {
                throw new RuntimeException("test");
            }
            Tools.randomPause(100);
        }
    }
}
