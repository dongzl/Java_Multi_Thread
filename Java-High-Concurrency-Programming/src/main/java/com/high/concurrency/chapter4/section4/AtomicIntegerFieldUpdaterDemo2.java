package com.high.concurrency.chapter4.section4;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/29 下午5:54
 */
public class AtomicIntegerFieldUpdaterDemo2 {

    public static class Candidate {
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    public static void main(String[] args) throws Exception {
        final Candidate stu = new Candidate();
        Thread[] threads = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    scoreUpdater.incrementAndGet(stu);
                }
            };
            threads[i].start();
        }

        for (int i = 0; i < 10000; i++) {
            threads[i].join();
        }

        System.out.println("score=" + stu.score);
    }
}
