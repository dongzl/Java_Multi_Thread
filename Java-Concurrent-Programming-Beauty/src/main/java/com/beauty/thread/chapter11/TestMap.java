package com.beauty.thread.chapter11;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dongzonglei
 * @description
 * @date 2019-04-10 09:26
 */
public class TestMap {

    static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String args[]) throws Exception {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new ArrayList<>();
                list1.add("device1");
                list1.add("device2");
                map.put("topice1", list1);
                System.out.println(Objects.toString(map));
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new ArrayList<>();
                list1.add("device11");
                list1.add("device22");
                map.put("topice1", list1);
                System.out.println(Objects.toString(map));
            }
        });

        Thread threadThree = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new ArrayList<>();
                list1.add("device111");
                list1.add("device222");
                map.put("topice2", list1);
                System.out.println(Objects.toString(map));
            }
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }
}
