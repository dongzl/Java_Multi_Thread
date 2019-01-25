package com.multi.thread.guide.core.chapter7.section1;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/25 下午2:20
 */
public class DeadlockingPhilosopher extends AbstractPhilosopher {

    public DeadlockingPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (left) {
            System.out.println();
            left.pickUp();
            synchronized (right) {
                System.out.println();
                right.pickUp();
                doEat();
                right.putDown();
            }
            left.putDown();
        }
    }
}
