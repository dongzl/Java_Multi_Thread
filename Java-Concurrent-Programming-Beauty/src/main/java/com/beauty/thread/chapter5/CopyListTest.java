package com.beauty.thread.chapter5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-18 09:54
 */
public class CopyListTest {

    private static volatile CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public static void main(String args[]) throws Exception {
        copyOnWriteArrayList.add("hello");
        copyOnWriteArrayList.add("alibaba");
        copyOnWriteArrayList.add("welcome");
        copyOnWriteArrayList.add("to");
        copyOnWriteArrayList.add("hangzhou");

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                copyOnWriteArrayList.set(1, "baba");
                copyOnWriteArrayList.remove(2);
                copyOnWriteArrayList.remove(3);
            }
        });

        Iterator<String> iter = copyOnWriteArrayList.iterator();
        threadOne.start();
        threadOne.join();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
