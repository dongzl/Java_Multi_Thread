package com.high.concurrency.chapter7.section13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/08 下午12:03
 */
public class PosValue {

    final double value;

    final List<Double> x;

    public PosValue(double value, List<Double> x) {
        this.value = value;
        List<Double> b = new ArrayList<>(5);
        b.addAll(x);
        this.x = Collections.unmodifiableList(b);
    }

    public double getValue() {
        return value;
    }

    public List<Double> getX() {
        return x;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("value:").append(value).append("\n").append(x.toString());
        return buffer.toString();
    }
}
