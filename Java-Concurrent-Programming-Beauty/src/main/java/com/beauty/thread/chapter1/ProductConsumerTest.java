package com.beauty.thread.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-09 09:25
 */
public class ProductConsumerTest {

    private List<String> queue = new ArrayList<>();

    private Integer MAX_SIZE = 100;

    public void produce(String ele) {
        synchronized (queue) {
            while (queue.size() == MAX_SIZE) {
                try {
                    queue.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            queue.add(ele);
            queue.notifyAll();
        }
    }

    public String consume() {
        synchronized (queue) {
            while (queue.size() == 0) {
                try {
                    queue.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String a = queue.get(0);
        queue.remove(0);
        queue.notifyAll();
        return a;
    }
}
