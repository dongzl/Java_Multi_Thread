package com.high.concurrency.chapter6.section2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/04 下午7:23
 */
public class InstanceMethodRef {

    public static void main(String[] args) throws Exception {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i, "billy" + Integer.toString(i)));
        }
        users.stream().map(User::getName).forEach(System.out::println);
    }
}
