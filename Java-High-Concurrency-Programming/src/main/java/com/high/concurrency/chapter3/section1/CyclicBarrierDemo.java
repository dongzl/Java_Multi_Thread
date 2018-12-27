package com.high.concurrency.chapter3.section1;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/26 下午7:48
 */
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable {

        private String soldier;

        private final CyclicBarrier cyclic;

        public Soldier(CyclicBarrier cyclic, String soldier) {
            this.soldier = soldier;
            this.cyclic = cyclic;
        }

        public void run() {
            try {
                cyclic.await();
                doWork();
                cyclic.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt(10) * 1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ":任务完成");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        public void run() {
            if (flag) {
                System.out.println("司令：[士兵" + N + "个，任务完成！]");
            } else {
                System.out.println("司令：[士兵" + N + "个，集合完毕！]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final int N = 10;
        Thread[] allSolider = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍！");
        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道！");
            allSolider[i] = new Thread(new Soldier(cyclic, "士兵" + i));
            allSolider[i].start();
//            if (i == 5) {
//                allSolider[i].interrupt();
//            }
        }
    }
}
