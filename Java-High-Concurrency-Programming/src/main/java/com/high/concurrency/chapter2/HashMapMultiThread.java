package com.high.concurrency.chapter2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 下午12:03
 */
public class HashMapMultiThread {

    static Map<String, String> map = new HashMap<String, String>();

    public static class AddThread implements Runnable {
        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        public void run() {
            for (int i = start; i < 100000; i += 2) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new AddThread(0));
        Thread t2 = new Thread(new AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
