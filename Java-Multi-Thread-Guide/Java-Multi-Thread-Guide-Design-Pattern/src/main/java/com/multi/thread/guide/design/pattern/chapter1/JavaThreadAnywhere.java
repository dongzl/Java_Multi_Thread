package com.multi.thread.guide.design.pattern.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-14 14:26
 */
public class JavaThreadAnywhere {

    public static void main(String args[]) throws Exception {
        System.out.println("The main method was executed by thread:" + Thread.currentThread().getName());
        Helper helper = new Helper("Java Thread Anywhere");
        helper.run();
    }

    static class Helper implements Runnable {

        private final String message;

        public Helper(String message) {
            this.message = message;
        }

        private void doSomething(String message) {
            System.out.println("The doSomething method was executed by thread:" + Thread.currentThread().getName());
            System.out.println("Do something with:" + message);
        }

        @Override
        public void run() {
            doSomething(message);
        }
    }
}
