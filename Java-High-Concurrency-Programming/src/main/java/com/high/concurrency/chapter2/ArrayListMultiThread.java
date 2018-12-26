package com.high.concurrency.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 上午11:58
 */
public class ArrayListMultiThread {

    static List<Integer> list = new ArrayList<Integer>();

    public static class AddThread implements Runnable {

        public void run() {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
    }
}
