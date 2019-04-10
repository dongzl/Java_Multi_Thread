package com.beauty.thread.chapter11;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-10 09:47
 */
public class TestSimpleDateFormat3 {

    static ThreadLocal<DateFormat> safeSdf = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String args[]) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(safeSdf.get().parse("2017-09-08 23:56:34"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        safeSdf.remove();
                    }
                }
            });
            thread.start();
        }
    }
}
