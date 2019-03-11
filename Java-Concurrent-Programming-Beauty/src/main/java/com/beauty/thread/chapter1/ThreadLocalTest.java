package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-11 17:29
 */
public class ThreadLocalTest {

    static void print(String str) {
        System.out.println(str + ":" + localVariable.get());
        localVariable.remove();
    }

    static ThreadLocal<String> localVariable = new ThreadLocal<>();
    
    public static void main(String args[]) throws Exception {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadOne local variable");
                print("threadOne");
                System.out.println("threadOne remove after:" + localVariable.get());
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadTwo local variable");
                print("threadTwo");
                System.out.println("threadTwo remove after:" + localVariable.get());
            }
        });

        threadOne.start();
        threadTwo.start();
    }
}
