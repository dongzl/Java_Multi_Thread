package com.multi.thread.guide.core.chapter2.section6;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/13 下午12:10
 */
public class SpeculativeLoadExample {

    private boolean ready = false;
    private int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};


    public void write() {
        int[] newData = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < newData.length; i++) {
            newData[i] = newData[i] - i;
        }
        data = newData;
        ready = true;
    }

    public int reader() {
        int sum = 0;
        int[] snapshot;
        if (ready) {
            snapshot = data;
            for (int i = 0; i < snapshot.length; i++) {
                sum += snapshot[i];
            }
        }
        return sum;
    }
 }
