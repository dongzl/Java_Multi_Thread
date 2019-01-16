package com.multi.thread.guide.core.chapter4.section3;

import com.multi.thread.guide.core.Tools;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/16 下午5:32
 */
public class Storage implements Closeable, AutoCloseable {

    private final RandomAccessFile storageFile;

    private final FileChannel storageChannel;

    protected final AtomicLong totalWrites = new AtomicLong(0);

    public Storage(long fileSize, String fileShortName) throws IOException {
        String fullFileName = System.getProperty("java.io.tmpdir") + "/" + fileShortName;
        String localFileName;
        localFileName = createStoreFile(fileSize, fullFileName);
        storageFile = new RandomAccessFile(localFileName, "rw");
        storageChannel = storageFile.getChannel();
    }

    public int store(long offset, ByteBuffer byteBuf) throws IOException {
        int length;
        storageChannel.write(byteBuf, offset);
        length = byteBuf.limit();
        totalWrites.addAndGet(length);
        return length;
    }

    public long getTotalWrites() {
        return totalWrites.get();
    }

    private String createStoreFile(final long fileSize, String fullFileName) throws IOException {
        File file = new File(fullFileName);
        System.out.println("create local file:" + fullFileName);
        RandomAccessFile raf;
        raf = new RandomAccessFile(file, "rw");
        try {
            raf.setLength(fileSize);
        } finally {
            Tools.silentClose(raf);
        }
        return fullFileName;
    }

    @Override
    public synchronized void close() throws IOException {
        if (storageChannel.isOpen()) {
            Tools.silentClose(storageChannel, storageFile);
        }
    }
}
