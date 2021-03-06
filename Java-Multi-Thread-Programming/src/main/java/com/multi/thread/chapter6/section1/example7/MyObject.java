package com.multi.thread.chapter6.section1.example7;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2018/12/23 上午10:50
 */
public class MyObject {

    private static volatile MyObject myObject;

    private MyObject() {

    }

    public static MyObject getInstance() {
        try {
            if (myObject != null) {
            } else {
                Thread.sleep(3000);
                synchronized (MyObject.class) {
                    if (myObject == null) {
                        myObject = new MyObject();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myObject;
    }
}
