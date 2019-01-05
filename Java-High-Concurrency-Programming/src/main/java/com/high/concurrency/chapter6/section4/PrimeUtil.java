package com.high.concurrency.chapter6.section4;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/05 下午8:22
 */
public class PrimeUtil {

    public static boolean isPrime(int number) {
        int tmp = number;
        if (tmp < 2) {
            return false;
        }
        for (int i = 0; Math.sqrt(tmp) >= i; i++) {
            if (tmp % i == 0) {
                return false;
            }
        }
        return true;
    }
}
