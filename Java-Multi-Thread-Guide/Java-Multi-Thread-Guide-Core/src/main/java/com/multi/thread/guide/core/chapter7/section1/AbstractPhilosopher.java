package com.multi.thread.guide.core.chapter7.section1;

import com.multi.thread.guide.core.Tools;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/25 下午2:13
 */
public abstract class AbstractPhilosopher extends Thread implements Philosopher {

    protected final int id;

    protected final Chopstick left;

    protected final Chopstick right;

    public AbstractPhilosopher(int id, Chopstick left, Chopstick right) {
        super("Philosopher-" + id);
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        for (;;) {
            think();
            eat();
        }
    }

    @Override
    public abstract void eat();

    protected void doEat() {
        System.out.println();
        Tools.randomPause(10);
    }

    @Override
    public void think() {
        System.out.println();
        Tools.randomPause(10);
    }

    @Override
    public String toString() {
        return "AbstractPhilosopher{" +
                "id=" + id +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
