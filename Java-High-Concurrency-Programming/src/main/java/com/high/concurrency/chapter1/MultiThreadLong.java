package com.high.concurrency.chapter1;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/25 下午5:39
 */
public class MultiThreadLong {

    public static long t = 0;

    public static class ChangeT implements Runnable {

        public long to;

        public ChangeT(long to) {
            this.to = to;
        }

        public void run() {
            while (true) {
                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable {
        public void run() {
            while (true) {
                long tmp = MultiThreadLong.t;
                if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L) {
                    System.out.println(tmp);
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }
}
