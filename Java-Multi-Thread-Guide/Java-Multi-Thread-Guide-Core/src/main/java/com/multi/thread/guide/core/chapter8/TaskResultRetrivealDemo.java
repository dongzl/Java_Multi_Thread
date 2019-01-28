package com.multi.thread.guide.core.chapter8;

import com.multi.thread.guide.core.Tools;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/28 下午12:04
 */
public class TaskResultRetrivealDemo {

    final static int N_CPU = Runtime.getRuntime().availableProcessors();

    final ThreadPoolExecutor executor = new ThreadPoolExecutor(0, N_CPU * 2, 4,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws Exception {
        TaskResultRetrivealDemo demo = new TaskResultRetrivealDemo();
        Future<String> future = demo.recognizeImage("/tmp/image/0001.jpg");
        doSomething();
        try {
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doSomething() {
        Tools.randomPause(200);
    }

    public Future<String> recognizeImage(final String imageFile) {
        return executor.submit(new Callable<String>(){
            @Override
            public String call() throws Exception {
                return doRecognizeImage(new File(imageFile));
            }
        });
    }

    public String doRecognizeImage(File imageFile) {
        String result = null;
        String[] simulatedResults = {"苏z MM518", "苏z MM519", "苏z MM517"};
        result = simulatedResults[(int) (Math.random() * simulatedResults.length)];
        Tools.randomPause(100);
        return result;
    }
}
