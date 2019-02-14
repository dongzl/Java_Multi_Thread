package com.multi.thread.guide.design.pattern.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-14 14:45
 */
public class ThreadCreationViaSubclass {

    public static void main(String args[]) throws Exception {
        Thread thread = new CustomThread();
        thread.start();
    }

    static class CustomThread extends Thread {
        @Override
        public void run() {
            System.out.println("Running...");
        }
    }
}
