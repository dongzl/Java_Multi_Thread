package com.multi.thread.chapter7.section4.example3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/24 下午5:10
 */
public class MyThread extends Thread {

    private SimpleDateFormat sdf;

    private String dateString;

    public MyThread(SimpleDateFormat sdf, String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            Date dateRef = DateTools.getSimpleDateFormat("yyyy-MM-dd").parse(dateString);
            String newDateString = DateTools.getSimpleDateFormat("yyyy-MM-dd").format(dateRef);
            if (!newDateString.equals(dateString)) {
                System.out.println("ThreadName = " + this.getName() + "报错了，日期字符串：" + dateString + "转换成的日期为：" + newDateString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
