package com.multi.thread.guide.core.chapter3.section8;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/14 下午4:48
 */
public class VolatileOrderingDemo {

    private int dataA = 0;

    private long dataB = 0L;

    private String dataC = null;

    private volatile boolean ready = false;

    public void writer() {
        dataA = 1;
        dataB = 1000L;
        dataC = "Content...";
        ready = true;
    }

    public int reader() {
        int result = 0;
        boolean allISOK;
        if (ready) {
            allISOK = (1 == dataA) && (1000L == dataB) && "Content...".equals(dataC);
            result = allISOK ? 1 : 2;
        } else {
            result = 3;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

    }
}
