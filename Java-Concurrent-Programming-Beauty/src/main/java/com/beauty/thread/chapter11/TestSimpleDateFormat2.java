package com.beauty.thread.chapter11;

import java.text.SimpleDateFormat;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-10 09:47
 */
public class TestSimpleDateFormat2 {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String args[]) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (sdf) {
                            System.out.println(sdf.parse("2017-09-08 23:56:34"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}
