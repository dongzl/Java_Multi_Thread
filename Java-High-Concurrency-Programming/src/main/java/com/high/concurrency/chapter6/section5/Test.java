package com.high.concurrency.chapter6.section5;

import java.util.concurrent.CompletableFuture;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/05 下午8:43
 */
public class Test {

    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(() -> calc(50))
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        fu.get();


        CompletableFuture<Void> fu1 = CompletableFuture.supplyAsync(() -> calc(50))
                .exceptionally(ex -> {
                    System.out.println(ex.toString());
                    return 0;
                })
                .thenApply((i) -> Integer.toString(i))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        fu1.get();

        CompletableFuture<Void> fu2 = CompletableFuture.supplyAsync(() -> calc(50))
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> calc(50)))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        fu2.get();

        CompletableFuture<Integer> intFuture = CompletableFuture.supplyAsync(() -> calc(50));

        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(() -> calc(50));

        CompletableFuture<Void> fu3 = intFuture.thenCombine(intFuture2, (i, j) -> (i + j))
                .thenApply((str) -> "\"" + str + "\"")
                .thenAccept(System.out::println);
        fu3.get();
    }

    public static Integer calc(Integer para) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        return para * para;
    }
}
