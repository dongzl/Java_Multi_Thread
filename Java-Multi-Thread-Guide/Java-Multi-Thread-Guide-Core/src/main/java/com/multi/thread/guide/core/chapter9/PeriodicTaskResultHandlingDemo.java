package com.multi.thread.guide.core.chapter9;

import com.multi.thread.guide.core.Tools;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-01-31 17:15
 */
public class PeriodicTaskResultHandlingDemo {

    final static ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public static void main(String args[]) throws Exception {
        final String host = args[0];
        final AsyncTask<Integer> asyncTask = new AsyncTask<Integer>(ses) {
            final Random rnd = new Random();
            final String targetHost = host;
            @Override
            protected void onResult(Integer result) {
                saveToDatabase(result);
            }

            @Override
            public Integer call() throws Exception {
                return pingHost();
            }

            private Integer pingHost() throws Exception {
                Tools.randomPause(2000);
                Integer r = Integer.valueOf(rnd.nextInt(4));
                return r;
            }

            private void saveToDatabase(Integer result) {
                System.out.println("");
            }

            @Override
            public String toString() {
                return "Ping " + targetHost + "," + super.toString();
            }
        };

        ses.scheduleWithFixedDelay(asyncTask, 0, 3, TimeUnit.SECONDS);
    }
}
