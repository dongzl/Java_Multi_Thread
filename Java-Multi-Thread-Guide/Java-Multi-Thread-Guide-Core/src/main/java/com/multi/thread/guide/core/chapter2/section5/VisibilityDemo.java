package com.multi.thread.guide.core.chapter2.section5;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/12 下午7:16
 */
public class VisibilityDemo {

    public static void main(String[] args) throws Exception {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(new TimeConsumingTask());
        thread.start();
        Thread.sleep(10000);
        timeConsumingTask.cancel();
    }

    static class TimeConsumingTask implements Runnable {
//        private boolean toCancel = false;

        private volatile boolean toCancel = false;

        @Override
        public void run() {
            while (!toCancel) {
                if (doExecute()) {
                    break;
                }
                if (toCancel) {
                    System.out.println("Task was canceled.");
                } else {
                    System.out.println("Task done.");
                }
            }
        }

        private boolean doExecute() {
            boolean isDone = false;
            System.out.println("executing...");
            try {
                Thread.sleep(50);
            } catch (Exception e) {

            }
            return isDone;
        }

        public void cancel() {
            toCancel = true;
            System.out.println(this + "canceled.");
        }
    }
}
