package com.high.concurrency.chapter5.pc;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/01 下午9:24
 */
public class Consumer implements Runnable {

    private BlockingQueue<PCData> queue;

    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id = " + Thread.currentThread().getId());
        Random random = new Random();
        try {
            while (true) {
                PCData data = queue.take();
                if (null != data) {
                    int re = data.getIntData() * data.getIntData();
                    System.out.println(MessageFormat.format("{0} * {1} = {2}", data.getIntData(), data.getIntData(), re));
                    Thread.sleep(random.nextInt(SLEEPTIME));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
