package com.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author dongzonglei
 * @description
 * @date 2019-09-11 18:44
 */
public class CyclicBarrierTest {

    public static void main(String args[]) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < barrier.getParties(); i++) {
            new Thread(new MyRunnable(barrier), "队友"+i).start();
        }
        System.out.println("main function is finished.");
    }

    private static class MyRunnable implements Runnable{
        private CyclicBarrier barrier;

        public MyRunnable(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            for(int i = 0; i < 3; i++) {
                try {
                    Random rand = new Random();
                    int randomNum = rand.nextInt((3000 - 1000) + 1) + 1000;//产生1000到3000之间的随机整数
                    Thread.sleep(randomNum);
                    System.out.println(Thread.currentThread().getName() + ", 通过了第"+i+"个障碍物, 使用了 "+((double)randomNum/1000)+"s");
                    this.barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
