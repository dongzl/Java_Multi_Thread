package com.high.concurrency.chapter6.section7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/06 下午9:01
 */
public class LongAdderDemo {

    private static final int MAX_THREADS = 3;

    private static final int TASK_COUNT = 3;

    private static final int TRAGET_COUNT = 3;

    private AtomicLong account = new AtomicLong(0L);

    private LongAdder laccount = new LongAdder();

    private long count = 0;

    static CountDownLatch cdlsync = new CountDownLatch(TASK_COUNT);

    static CountDownLatch cdlatomic = new CountDownLatch(TASK_COUNT);

    static CountDownLatch cdladdr = new CountDownLatch(TASK_COUNT);

    protected synchronized long inc() {
        return ++count;
    }

    protected synchronized long getCount() {
        return count;
    }

    public class SyncThread implements Runnable {
        protected String name;

        protected long startTime;

        LongAdderDemo out;

        public SyncThread(LongAdderDemo out, long startTime) {
            this.startTime = startTime;
            this.out = out;
        }

        @Override
        public void run() {
            long v = out.getCount();
            while (v < TRAGET_COUNT) {
                v = out.inc();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SyncThread speed:" + (endTime - startTime) + "ms" + " v = " + v);
            cdlsync.countDown();
        }
    }

    public void testSync() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        SyncThread sync = new SyncThread(this, startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exec.submit(sync);
        }
        cdlsync.await();
        exec.shutdown();
    }

    public class AtomicThread implements Runnable {
        protected String name;

        protected long startTime;

        public AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = account.get();
            while (v < TRAGET_COUNT) {
                v = account.incrementAndGet();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AtomicThread speed:" + (endTime - startTime) + "ms" + " v = " + v);
            cdlatomic.countDown();
        }
    }

    public void testAtomic() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        AtomicThread atomic = new AtomicThread(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exec.submit(atomic);
        }
        cdlatomic.await();
        exec.shutdown();
    }

    public class LongAddrThread implements Runnable {
        protected String name;

        protected long startTime;

        public LongAddrThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = laccount.sum();
            while (v < TRAGET_COUNT) {
                laccount.increment();
                v = laccount.sum();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("LongAddrThread speed:" + (endTime - startTime) + "ms" + " v = " + v);
            cdladdr.countDown();
        }
    }

    public void testAtomicLong() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        AtomicThread atomic = new AtomicThread(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exec.submit(atomic);
        }
        cdladdr.await();
        exec.shutdown();
    }
}
