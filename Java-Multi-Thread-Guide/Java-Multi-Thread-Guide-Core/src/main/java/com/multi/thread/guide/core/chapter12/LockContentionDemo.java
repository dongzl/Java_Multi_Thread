package com.multi.thread.guide.core.chapter12;

import com.multi.thread.guide.core.Tools;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-13 11:06
 */
public class LockContentionDemo {

    static long lockDuration = 100;

    static ShardResource sr = new ShardResource();

    static long lockAccessFrequency = 50;

    public static void main(String args[]) throws Exception {
        int argc = args.length;
        if (argc > 0) {
            lockDuration = Integer.valueOf(args[0]);
            if (argc > 1) {
                lockAccessFrequency = Long.valueOf(args[1]);
            }
        }
        int N = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[N];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    for (;;) {
                        sr.access();
                        try {
                            Thread.sleep(lockAccessFrequency);
                        } catch (Exception e) {

                        }
                    }
                }
            };
        }
//        Tools.

    }

    static class ShardResource {
        public synchronized void access() {
            try {
                Thread.sleep(lockDuration);
            } catch (Exception e) {

            }
        }
    }
}
