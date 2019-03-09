package com.beauty.thread.chapter1;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-09 09:40
 */
public class WaitNotifyTnterupt {
    
    static Object obj = new Object();
    
    public static void main(String args[]) throws Exception {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("-----begin------");
                    synchronized (obj) {
                        obj.wait();
                        obj.wait(1, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        Thread.sleep(1000);
        System.out.println("----begin interrupt threadA-----");
        threadA.interrupt();
        System.out.println("----end interrupt threadA----");
    }
}
