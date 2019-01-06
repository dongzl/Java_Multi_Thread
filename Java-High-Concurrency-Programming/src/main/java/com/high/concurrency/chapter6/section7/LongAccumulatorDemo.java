package com.high.concurrency.chapter6.section7;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/06 下午9:25
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) throws Exception {
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
        Thread[] ts = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            ts[i] = new Thread(() -> {
                Random random = new Random();
                long value = random.nextLong();
                accumulator.accumulate(value);
            });
            ts[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            ts[i].join();
        }
        System.out.println(accumulator.longValue());
    }
}
