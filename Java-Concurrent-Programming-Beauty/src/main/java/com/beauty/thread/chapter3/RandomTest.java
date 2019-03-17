package com.beauty.thread.chapter3;

import java.util.Random;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-17 21:38
 */
public class RandomTest {

    public static void main(String args[]) throws Exception {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
