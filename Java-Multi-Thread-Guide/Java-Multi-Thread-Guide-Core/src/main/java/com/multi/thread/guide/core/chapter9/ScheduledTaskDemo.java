package com.multi.thread.guide.core.chapter9;

import com.multi.thread.guide.core.Tools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dongzonglei
 * @description
 * @date 2019-01-31 15:56
 */
public class ScheduledTaskDemo {

    static ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);

    public static void main(String args[]) throws Exception {
        final int argc = args.length;
        int maxConsumption;
        int minConsumption;
        if (argc > 2) {
            minConsumption = Integer.valueOf(args[0]);
            maxConsumption = Integer.valueOf(args[1]);
        } else {
            minConsumption = maxConsumption = 1000;
        }
        ses.scheduleAtFixedRate(new SimulatedTask(minConsumption, maxConsumption, "ScheduleAtFixedRate"), 0, 2, TimeUnit.SECONDS);
        ses.scheduleWithFixedDelay(new SimulatedTask(minConsumption, maxConsumption, "ScheduleWithFixedDelay"), 0, 1, TimeUnit.SECONDS);
        Thread.sleep(20000);
        ses.shutdown();
    }

    static class SimulatedTask implements Runnable {
        private String name;

        private final int maxConsumption;

        private final int minConsumption;

        private final AtomicInteger seq = new AtomicInteger(0);

        public SimulatedTask(int maxConsumption, int minConsumption, String name) {
            this.name = name;
            this.maxConsumption = maxConsumption;
            this.minConsumption = minConsumption;
        }

        @Override
        public void run() {
            try {
                Tools.randomPause(maxConsumption);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
