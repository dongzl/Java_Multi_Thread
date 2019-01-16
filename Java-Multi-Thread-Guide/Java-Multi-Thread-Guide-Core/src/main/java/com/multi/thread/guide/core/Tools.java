package com.multi.thread.guide.core;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile; /**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/12 下午7:48
 */
public class Tools {

    public static void randomPause(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
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
