package com.beauty.thread.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-09 08:49
 */
public class CallerTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "hello";
    }

    public static void main(String args[]) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
