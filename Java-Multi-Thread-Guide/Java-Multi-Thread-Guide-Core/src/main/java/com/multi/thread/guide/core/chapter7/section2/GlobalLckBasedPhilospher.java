package com.multi.thread.guide.core.chapter7.section2;

import com.multi.thread.guide.core.chapter7.section1.AbstractPhilosopher;
import com.multi.thread.guide.core.chapter7.section1.Chopstick;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/25 下午4:59
 */
public class GlobalLckBasedPhilospher extends AbstractPhilosopher {

    private static final Object GLOBAL_LOCK = new Object();

    public GlobalLckBasedPhilospher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (GLOBAL_LOCK) {
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
