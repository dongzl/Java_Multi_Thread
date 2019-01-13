package com.thread;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/13 下午8:51
 */
public class LongAdderDemo1 {

    private static final int TARGET_COUNT = 10000000;

    private LongAdder lacount = new LongAdder();

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
        }
    }
}
