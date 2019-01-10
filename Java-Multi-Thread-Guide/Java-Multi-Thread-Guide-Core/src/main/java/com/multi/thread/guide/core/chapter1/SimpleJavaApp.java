package com.multi.thread.guide.core.chapter1;

import java.util.Date;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/10 上午11:47
 */
public class SimpleJavaApp {

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println(new Date());
            Thread.sleep(1000);
        }
    }
}
