package com.multi.thread.guide.core.chapter12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-13 14:50
 */
public class ImplicitControlThreadsCount {

    final ExecutorService executorService = Executors.newCachedThreadPool();

    final Semaphore semaphore = new Semaphore(Runtime.getRuntime().availableProcessors() * 2);

    public void doSomething(final String data) throws Exception {
        semaphore.acquire();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    process(data);
                } catch (Exception e) {
                    semaphore.release();
                }
            }
        };
        executorService.submit(task);
    }

    private void process(String data) {
        //
    }
}
