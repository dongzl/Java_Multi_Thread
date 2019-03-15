package com.beauty.thread.chapter2;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-15 09:59
 */
public class TestForContent2 {

    static final int LINE_NUM =1024;

    static final int COLUM_NUM = 1024;

    public static void main(String args[]) throws Exception {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[j][i] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime - startTime;
        System.out.println("cache time:" + cacheTime);
    }
}
