package com.multi.thread.guide.core.chapter7.section1;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/25 下午5:16
 */
public class FixedPhilosopher extends AbstractPhilosopher {

    private final Chopstick one;

    private final Chopstick theOther;

    public FixedPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
        int leftHash = System.identityHashCode(left);
        int rightHash = System.identityHashCode(right);
        if (leftHash < rightHash) {
            one = left;
            theOther = right;
        } else if (leftHash > rightHash) {
            one = right;
            theOther = left;
        } else {
            one = null;
            theOther = null;
        }
    }

    @Override
    public void eat() {
        if (null != one) {
            synchronized (one) {
                System.out.println();
                one.pickUp();
                synchronized (theOther) {
                    System.out.println();
                    theOther.pickUp();
                    doEat();
                    theOther.putDown();
                }
                one.putDown();
            }
        } else {
            synchronized (FixedPhilosopher.class) {
                System.out.println();
                left.pickUp();
                System.out.println();
                right.pickUp();
                doEat();
                right.putDown();
                left.putDown();
            }
        }
    }
}
