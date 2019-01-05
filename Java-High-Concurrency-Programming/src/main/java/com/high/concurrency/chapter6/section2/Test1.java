package com.high.concurrency.chapter6.section2;

import java.util.Arrays;
import java.util.function.IntConsumer;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午8:07
 */
public class Test1 {

    static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) throws Exception {
        for (int i : arr) {
            System.out.println(i);
        }

        Arrays.stream(arr).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        Arrays.stream(arr).forEach((final int x) -> {
            System.out.println(x);
        });

        Arrays.stream(arr).forEach((x) -> {
            System.out.println(x);
        });

        Arrays.stream(arr).forEach((x) -> System.out.println(x));

        Arrays.stream(arr).forEach(System.out::println);

        IntConsumer outPrintln = System.out::println;

        IntConsumer errPrintln = System.err::println;

        Arrays.stream(arr).forEach(outPrintln.andThen(errPrintln));
    }
}
