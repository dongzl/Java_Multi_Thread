package com.high.concurrency.chapter6.section1;

import java.util.Arrays;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午2:47
 */
public class Declarative {

    public static void imperative() {
        int[] iArr = {1, 3, 4, 6, 5, 7, 9, 8};
        for (int i = 0; i < iArr.length; i++) {
            System.out.println(i);
        }
    }

    public static void declarative() {
        int[] iArr = {1, 3, 4, 6, 5, 7, 9, 8};
        Arrays.stream(iArr).forEach(System.out::println);
    }

    public static void test() {
        int[] iArr = {1, 3, 4, 6, 5, 7, 9, 8};
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] % 2 != 0) {
                iArr[i]++;
            }
            System.out.println(iArr[i]);
        }

        Arrays.stream(iArr).map(x -> (x % 2 == 0 ? x : x+1)).forEach(System.out::println);
    }
}
