package com.beauty.thread.chapter11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-11 09:38
 */
public class TestShutDown {

    static void asynExecuteOne() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("--asyn execute one---");
            }
        });
        executorService.shutdown();
    }

    static void asynExecuteTwo() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("--asyn execute two---");
            }
        });
        executorService.shutdown();
    }

    public static void main(String args[]) throws Exception {
        System.out.println("--asyn execute--");
        asynExecuteOne();
        asynExecuteTwo();
        System.out.println("--execute over--");
    }
}
