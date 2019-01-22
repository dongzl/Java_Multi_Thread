package com.multi.thread.guide.core;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/12 下午7:48
 */
public class Tools {

    public static void randomPause(int time) {
        Random rnd = new Random();
        int sleepTime = rnd.nextInt(time);
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void silentClose(Closeable... closeable) {
        try {
            for (Closeable c : closeable) {
                if (c != null) {
                    c.close();
                }
            }
        } catch (Exception e) {

        }
    }
}
