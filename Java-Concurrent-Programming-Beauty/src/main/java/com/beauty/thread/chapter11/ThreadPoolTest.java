package com.beauty.thread.chapter11;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-11 10:05
 */
public class ThreadPoolTest {

    static class LocalVariable {
        private Long[] a = new Long[1024 * 1024];
    }

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    final static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<>();

    public static void main(String args[]) throws Exception {
        for (int i = 0; i < 50; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    localVariable.set(new LocalVariable());
                    System.out.println("use local variable");
                    //localVariable.remove();
                }
            });

            Thread.sleep(1000);
        }

        System.out.println("pool execute over");
    }
}
