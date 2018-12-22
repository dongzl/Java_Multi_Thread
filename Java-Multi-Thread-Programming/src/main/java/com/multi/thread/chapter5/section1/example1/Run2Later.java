package com.multi.thread.chapter5.section1.example1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/22 下午12:50
 */
public class Run2Later {

    private static Timer timer = new Timer();

    public static class MyTask1 extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("1 begin 运行了！时间为：" + new Date());
                Thread.sleep(20000);
                System.out.println("1 end 运行了！时间为：" + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyTask2 extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("1 begin 运行了！时间为：" + new Date());
                Thread.sleep(20000);
                System.out.println("1 end 运行了！时间为：" + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            MyTask1 task1 = new MyTask1();
            MyTask2 task2 = new MyTask2();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString1 = "2018-12-22 12:57:00";
            String dateString2 = "2018-12-22 12:57:00";
            Date dateRef1 = sdf1.parse(dateString1);
            Date dateRef2 = sdf2.parse(dateString2);
            System.out.println("字符串时间：" + dateRef1.toLocaleString() + "当前时间：" + new Date().toLocaleString());
            timer.schedule(task1, dateRef1);
            timer.schedule(task2, dateRef2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
