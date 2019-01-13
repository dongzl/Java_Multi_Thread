package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/13 下午8:51
 */
public class LongAdderDemo {

    private static final int MAX_THREADS = 3;

    private static final int TASK_COUNT = 3;

    private static final int TARGET_COUNT = 10000000;

    private LongAdder lacount = new LongAdder();

    private static CountDownLatch cdladdr = new CountDownLatch(TASK_COUNT);

    public class LongAdderThread implements Runnable {

        protected long starttime;

        public LongAdderThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while (v < TARGET_COUNT) {
                lacount.increment();
                v = lacount.sum();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("LongAdderThread spend:" + (endtime - starttime) + "ms" + " v" + v);
            cdladdr.countDown();
        }

    }

    public void testLongAdder() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        LongAdderThread atomic = new LongAdderThread(starttime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(atomic);
        }
        cdladdr.await();
        exe.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        LongAdderDemo demo = new LongAdderDemo();
        demo.testLongAdder();
    }
}
