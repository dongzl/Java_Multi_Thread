package com.high.concurrency.chapter6.section2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午7:17
 */
public class Test {

    public static void main(String[] args) throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers.forEach((Integer value) -> System.out.println(value));

        int num = 2;
        Function<Integer, Integer> stringConvert = (from) -> from * num;
        System.out.println(stringConvert.apply(3));


    }
}
