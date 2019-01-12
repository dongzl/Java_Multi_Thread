package com.multi.thread.guide.core.chapter2.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/12 下午5:04
 */
public class RaceConditionDemo {

    public static void main(String[] args) throws Exception {
        int numberOfThreads = args.length > 0 ? Short.valueOf(args[0]) : Runtime.getRuntime().availableProcessors();
        Thread[] workThreads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            workThreads[i] = new WorkThread(i, 10);
        }
        for (Thread ct : workThreads) {
            ct.start();
        }
    }

    static class WorkThread extends Thread {

        private final int requestCount;

        public WorkThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
            int i = requestCount;
            String requestID;
            RequestIDGenerator requestIDGen = RequestIDGenerator.getInstance();
            while (i-- > 0) {
                requestID = requestIDGen.nextID();
                processRequest(requestID);
            }
        }

        private void processRequest(String requestID) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.printf("%s got requestID: %s %n", Thread.currentThread().getName(), requestID);
        }
    }
}
