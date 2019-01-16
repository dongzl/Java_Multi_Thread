package com.multi.thread.guide.core.chapter4.section3;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/16 下午5:45
 */
public class DownloadBuffer implements Closeable {

    private long globalOffset;

    private long upperBound;

    private int offset = 0;

    private final ByteBuffer byteBuf;

    private final Storage storage;

    public DownloadBuffer(long globalOffset, long upperBound, Storage storage) {
        this.globalOffset = globalOffset;
        this.upperBound = upperBound;
        this.byteBuf = ByteBuffer.allocate(1024 * 1024);
        this.storage = storage;
    }

    public void write(ByteBuffer buf) throws IOException {
        int length = buf.position();
        final int capacity = byteBuf.capacity();
        if ((offset + length) > capacity || length == capacity) {
            flush();
        }
        byteBuf.position(offset);
        buf.flip();
        byteBuf.put(buf);
        offset += length;
    }

    public void flush() throws IOException {
        int length;
        byteBuf.flip();
        length = storage.store(globalOffset, byteBuf);
        byteBuf.clear();
        globalOffset += length;
        offset = 0;
    }

    @Override
    public void close() throws IOException {
        if (globalOffset < upperBound) {
            flush();
        }
    }
}
