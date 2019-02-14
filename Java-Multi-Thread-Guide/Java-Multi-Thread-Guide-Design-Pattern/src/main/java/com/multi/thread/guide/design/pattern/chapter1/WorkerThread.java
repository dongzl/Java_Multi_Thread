package com.multi.thread.guide.design.pattern.chapter1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-14 15:10
 */
public class WorkerThread {

    public static void main(String args[]) throws Exception {
        Helper helper = new Helper();
        helper.init();
        helper.submit("Something...");
    }

    static class Helper {
        private final BlockingQueue<String> workQueue = new ArrayBlockingQueue<String>(100);

        private final Thread workerThread = new Thread() {
            @Override
            public void run() {
                String task = null;
                while (true) {
                    try {
                        task = workQueue.take();
                    } catch (Exception e) {
                        break;
                    }
                    System.out.println(doProcess(task));
                }
            }
        };

        public void init() {
            workerThread.start();
        }

        protected String doProcess(String task) {
            return task + "->processed.";
        }

        public void submit(String task) {
            try {
                workQueue.put(task);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
