package com.high.concurrency.chapter7.section3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/07 上午9:45
 */
public final class ImmutableMessage {

    private final int sequenceNumber;

    private final List<String> values;

    public ImmutableMessage(int sequenceNumber, List<String> values) {
        this.sequenceNumber = sequenceNumber;
        this.values = Collections.unmodifiableList(new ArrayList<>(values));
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<String> getValues() {
        return values;
    }
}
