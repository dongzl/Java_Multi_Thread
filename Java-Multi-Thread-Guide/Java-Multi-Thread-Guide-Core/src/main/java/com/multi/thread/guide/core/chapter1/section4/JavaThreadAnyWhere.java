package com.multi.thread.guide.core.chapter1.section4;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/11 上午10:43
 */
public class JavaThreadAnyWhere {

    public static void main(String[] args) throws Exception {
        Thread currentThread = Thread.currentThread();
        String currentThreadName = currentThread.getName();
        System.out.printf("The main method was executed by thread:%s", currentThreadName);
        Helper helper = new Helper("Java Thread Anywhere");
        helper.run();
    }

    static class Helper implements Runnable {

        private final String message;

        public Helper(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            this.doSomething(message);
        }

        private void doSomething(String message) {
            Thread currentThread = Thread.currentThread();
            String currentThreadName = currentThread.getName();
            System.out.printf("The doSomething method was executed by thread:%s", currentThreadName);
            System.out.println("Do something with " + message);
        }
    }
}
