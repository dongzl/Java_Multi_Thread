package com.multi.thread.guide.core.chapter11;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-12 15:19
 */
public class RecordSet {

    public final int capacity;

    final String[] records = null;

    int readIndex = 0;
    int writeIndex = 0;

    public RecordSet(int capacity) {
        this.capacity = capacity;
    }

    public String nextRecord() {
        String record = null;
        if (readIndex < writeIndex) {
            record = records[readIndex++];
        }
        return record;
    }

    public boolean putRecord(String line) {
        if (writeIndex == capacity) {
            return true;
        }
        records[writeIndex++] = line;
        return false;
    }
}
