package com.multi.thread.guide.core.chapter2.section5;

import com.multi.thread.guide.core.Tools;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/12 下午7:46
 */
public class TheadJoinVisibility {

    static int data = 0;

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Tools.randomPause(50);
                data = 1;
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(data);
    }
}
