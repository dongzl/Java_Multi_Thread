package com.high.concurrency.chapter5.futureV2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/03 上午9:26
 */
public class FutureMain {

    public static void main(String[] args) throws Exception {
        FutureTask<String> future = new FutureTask<>(new RealData("a"));
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.submit(future);
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("数据 = " + future.get());
    }
}
