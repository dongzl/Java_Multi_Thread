package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-09 09:49
 */
public class NotifyTest {

    private static volatile Object resourceA = new Object();

    public static void main(String args[]) throws Exception {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                    try {Thread threadB = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (resourceA) {
                                System.out.println("threadB get resourceA lock");
                                try {
                                    System.out.println("threadB begin wait");
                                    resourceA.wait();
                                    System.out.println("rhreadB end wait");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                        System.out.println("threadA begin wait");
                        resourceA.wait();
                        System.out.println("rhreadA end wait");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    try {
                        System.out.println("threadB begin wait");
                        resourceA.wait();
                        System.out.println("rhreadB end wait");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadC begin notify");
                    //resourceA.notifyAll();
                    resourceA.notify();
                }
            }
        });
        
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
        
        threadA.join();
        threadB.join();
        threadC.join();
        
        System.out.println("main over");
    }
}
