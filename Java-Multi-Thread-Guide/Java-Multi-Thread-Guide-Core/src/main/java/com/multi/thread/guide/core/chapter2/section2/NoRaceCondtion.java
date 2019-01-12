package com.multi.thread.guide.core.chapter2.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/12 下午6:38
 */
public class NoRaceCondtion {

    public int nextSequence(int sequence) {
        if (sequence > 999) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }
}
