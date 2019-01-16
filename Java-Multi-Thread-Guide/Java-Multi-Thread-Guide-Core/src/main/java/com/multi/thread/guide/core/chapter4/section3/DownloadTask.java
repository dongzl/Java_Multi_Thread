package com.multi.thread.guide.core.chapter4.section3;

import com.multi.thread.guide.core.Tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/16 下午5:58
 */
public class DownloadTask implements Runnable {

    private final long lowerBound;

    private final long upperBound;

    private final DownloadBuffer xbuf;

    private final URL requestURL;

    private final AtomicBoolean cancelFlag;

    public DownloadTask(long lowerBound, long upperBound, URL requestURL, Storage storage, AtomicBoolean cancelFlag) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.requestURL = requestURL;
        this.cancelFlag = cancelFlag;
        this.xbuf= new DownloadBuffer(lowerBound, upperBound, storage);
    }

    private static InputStream issueRequest(URL requestURL, long lowerBound, long upperBound) throws IOException {
        return null;
    }

    @Override
    public void run() {
        if (cancelFlag.get()) {
            return;
        }
        ReadableByteChannel channel = null;
        try {
            channel = Channels.newChannel(issueRequest(requestURL, lowerBound, upperBound));
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (!cancelFlag.get() || channel.read(buf) > 0) {
                xbuf.write(buf);
                buf.clear();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            try {
                Tools.silentClose(channel, xbuf);
            } catch (Exception e) {

            }
        }
    }
}
