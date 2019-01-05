package com.high.concurrency.chapter6.section4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/05 下午8:23
 */
public class Test {

    public static void main(String[] args) throws Exception {
        IntStream.range(1, 1000000).filter(PrimeUtil::isPrime).count();

        IntStream.range(1, 1000000).parallel().filter(PrimeUtil::isPrime).count();

        List<Student> ss = new ArrayList<>();
        double ave = ss.stream().mapToInt(s -> s.getScore()).average().getAsDouble();

        double ave1 = ss.parallelStream().mapToInt(s -> s.getScore()).average().getAsDouble();

        int[] arr = new int[1000000];
        Arrays.parallelSort(arr);

        Random random = new Random();
        Arrays.setAll(arr, (i) -> random.nextInt());
        Arrays.parallelSetAll(arr, (i) -> random.nextInt());

    }
}
