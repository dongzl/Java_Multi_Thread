package com.high.concurrency.chapter6.section5;

import java.util.concurrent.CompletableFuture;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/05 下午8:35
 */
public class AskThread implements Runnable {

    CompletableFuture<Integer> re = null;

    public AskThread(CompletableFuture<Integer> re) {
        this.re = re;
    }

    @Override
    public void run() {
        int myRe = 0;
        try {
            myRe = re.get() * re.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(myRe);
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        Thread.sleep(1000);
        future.complete(60);

        final CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> calc(50));
        System.out.println(future1.get());
    }

    public static Integer calc(Integer para) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        return para * para;
    }
}
