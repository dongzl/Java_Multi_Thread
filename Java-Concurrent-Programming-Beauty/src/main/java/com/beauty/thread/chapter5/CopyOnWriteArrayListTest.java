package com.beauty.thread.chapter5;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-18 09:48
 */
public class CopyOnWriteArrayListTest {

    public static void main(String args[]) throws Exception {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("hello");
        arrayList.add("alibaba");
        Iterator<String> iter = arrayList.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
