package com.beauty.thread.chapter7;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-25 18:46
 */
public class TestDelay {

    static class DelayedEle implements Delayed {

        private final long delayTime;

        private final long expire;

        private String taskName;

        public DelayedEle(long delayTime, String taskName) {
            this.delayTime = delayTime;
            this.taskName = taskName;
            expire = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MICROSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(this.getDelay(TimeUnit.MICROSECONDS) - o.getDelay(TimeUnit.MICROSECONDS));
        }

        @Override
        public String toString() {
            return "DelayedEle{" +
                    "delayTime=" + delayTime +
                    ", expire=" + expire +
                    ", taskName='" + taskName + '\'' +
                    '}';
        }
    }

    public static void main(String args[]) throws Exception {
        DelayQueue<DelayedEle> delayQueue = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            DelayedEle delayedEle = new DelayedEle(random.nextInt(500), "task:" + i);
            delayQueue.offer(delayedEle);
        }
        DelayedEle ele = null;
        try {
            for (;;) {
                while ((ele = delayQueue.take()) != null) {
                    System.out.println(ele.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
