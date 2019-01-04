package com.high.concurrency.chapter6.section2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午7:28
 */
public class BadMethodRef {

    public static void main(String[] args) throws Exception {
        List<Double> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Double.valueOf(i));
        }

        //numbers.stream().map(Double::toString).forEach(System.out::println);
    }
}
