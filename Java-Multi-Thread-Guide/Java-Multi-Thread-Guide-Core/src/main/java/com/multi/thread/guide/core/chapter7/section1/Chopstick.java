package com.multi.thread.guide.core.chapter7.section1;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/25 下午2:13
 */
public class Chopstick {

    public final int id;

    private Status status = Status.PUT_DOWN;

    public Chopstick(int id) {
        super();
        this.id = id;
    }

    public void pickUp() {
        status = Status.PICKED_UP;
    }

    public void putDown() {
        status = Status.PUT_DOWN;
    }
}
