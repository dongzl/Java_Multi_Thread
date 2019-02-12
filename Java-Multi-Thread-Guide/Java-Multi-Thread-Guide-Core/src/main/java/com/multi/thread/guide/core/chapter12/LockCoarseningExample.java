package com.multi.thread.guide.core.chapter12;

import java.util.Random;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-12 18:22
 */
public class LockCoarseningExample {

    private final Random rnd = new Random();

    public void simulate() {
        int iq1 = randomIQ();
        int iq2 = randomIQ();
        int iq3 = randomIQ();
        act(iq1, iq2, iq3);
    }

    private void act(int... n) {

    }

    public int randomIQ() {
        return (int)Math.round(rnd.nextGaussian() * 15 + 100);
    }
}
