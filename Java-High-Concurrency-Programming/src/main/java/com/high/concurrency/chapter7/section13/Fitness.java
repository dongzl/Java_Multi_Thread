package com.high.concurrency.chapter7.section13;

import java.util.List;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/08 下午12:08
 */
public class Fitness {

    public static double fitness(List<Double> x) {
        double sum = 0;
        for (int i = 0; i < x.size(); i++) {
            sum += Math.sqrt(x.get(i));
        }
        return sum;
    }
}
