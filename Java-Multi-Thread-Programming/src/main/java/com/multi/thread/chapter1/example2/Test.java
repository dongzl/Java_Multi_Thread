package com.multi.thread.chapter1.example2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/04 下午9:28
 */
public class Test {

    public static void main(String[] args) throws Exception {
        try {
            MyThread myThread = new MyThread();
            myThread.setName("MyThread");
            myThread.start();
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("main = " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
