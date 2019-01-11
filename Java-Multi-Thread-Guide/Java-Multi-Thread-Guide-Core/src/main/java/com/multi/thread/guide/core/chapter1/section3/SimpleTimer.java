package com.multi.thread.guide.core.chapter1.section3;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/10 下午5:11
 */
public class SimpleTimer {

    private static int count;

    public static void main(String[] args) throws Exception {
        count = args.length >= 1 ? Integer.valueOf(args[0]) : 60;
        int remaining;
        while (true) {
            remaining = countDown();
            if (0 == remaining) {
                break;
            } else {
                System.out.println("Remianing " + count + " seconds(s)");
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Done.");
        }
    }

    private static int countDown() {
        return count--;
    }
}
