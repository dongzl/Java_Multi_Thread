package com.multi.thread.guide.core.chapter2.section2;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/12 下午6:39
 */
public class SafeCircularSeqGenerator implements CircularSeqGenerator {

    private short sequence = -1;

    @Override
    public synchronized short nextSequence() {
        if (sequence >= 99) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }
}
