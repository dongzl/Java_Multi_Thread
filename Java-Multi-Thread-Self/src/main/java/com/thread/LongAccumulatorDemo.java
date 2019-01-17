package com.thread;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/17 下午9:56
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) throws Exception {
        LongAdder addr = new LongAdder();
        addr.increment();
        System.out.println(addr.sum());
        System.out.println(addr.intValue());
        System.out.println(addr.sumThenReset());
        System.out.println(addr.sum());

        LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 0);
        accumulator.accumulate(1);
        System.out.println(accumulator.intValue());
        System.out.println(accumulator.get());
        System.out.println(accumulator.doubleValue());
        System.out.println(accumulator.floatValue());
        System.out.println(accumulator.getThenReset());
        System.out.println(accumulator.get());
    }
}
