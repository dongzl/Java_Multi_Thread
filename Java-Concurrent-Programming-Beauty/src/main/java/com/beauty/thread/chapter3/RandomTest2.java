package com.beauty.thread.chapter3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-17 21:38
 */
public class RandomTest2 {

    public static void main(String args[]) throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
