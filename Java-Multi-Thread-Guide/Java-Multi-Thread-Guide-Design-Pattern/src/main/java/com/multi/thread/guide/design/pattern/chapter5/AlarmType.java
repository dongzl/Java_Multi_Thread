package com.multi.thread.guide.design.pattern.chapter5;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-04 16:28
 */
public enum AlarmType {
    FAULT("fault"), RESUME("resume");

    private final String name;

    private AlarmType(String name) {
        this.name = name;
    }

    public String toString() {

        return name;
    }
}
