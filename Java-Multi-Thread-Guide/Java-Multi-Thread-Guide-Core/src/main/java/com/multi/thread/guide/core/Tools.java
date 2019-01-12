package com.multi.thread.guide.core;

/**
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
}
