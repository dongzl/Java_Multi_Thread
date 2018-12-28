package com.high.concurrency.chapter3.section2;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/27 下午8:14
 */
public class DivTask implements Runnable {

    int a, b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void run() {
        double re = a / b;
        System.out.println(re);
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            //pools.submit(new DivTask(100, i));
            pools.execute(new DivTask(100, i));
        }
    }
}
