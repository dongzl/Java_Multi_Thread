package com.multi.thread.guide.core.chapter4.section3;

import com.multi.thread.guide.core.Tools;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/16 下午6:05
 */
public class BigFileDownloader {

    protected final URL requestURL;

    protected final long fileSize;

    protected final Storage storage;

    protected final AtomicBoolean taskCanceled = new AtomicBoolean(false);

    public BigFileDownloader(String strURL) throws Exception {
        requestURL = new URL(strURL);
        fileSize = retieveFileSize(requestURL);
        System.out.println("file total size:" + fileSize);
        String fileName = strURL.substring(strURL.lastIndexOf("/") + 1);
        storage = new Storage(fileSize, fileName);
    }

    public void download(int taskCount, long reportInterval) throws Exception {
        long chunkSizePerThread = fileSize / taskCount;
        long lowerBound = 0;
        long upperBound = 0;
        DownloadTask dt;
        for (int i = taskCount - 1; i >=0; i--) {
            lowerBound = i * chunkSizePerThread;
            if (i == taskCount - 1) {
                upperBound = fileSize;
            } else {
                upperBound= lowerBound + chunkSizePerThread - 1;
            }
            dt = new DownloadTask(lowerBound, upperBound, requestURL, storage, taskCanceled);
            dispatchWork(dt, i);
        }
        reportProgress(reportInterval);
        doCleanup();
    }

    protected void doCleanup() {
        Tools.silentClose(storage);
    }

    protected void cancelDownload() {
        if (taskCanceled.compareAndSet(false, true)) {
            doCleanup();
        }
    }

    protected void dispatchWork(final DownloadTask dt, int workerIndex) {
        Thread workThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dt.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    cancelDownload();
                }
            }
        });
        workThread.setName("downloader-" + workerIndex);
        workThread.start();
    }

    private static long retieveFileSize(URL requestURL) throws Exception {
        return 0;
    }

    private void reportProgress(long reportInterval) throws InterruptedException {

    }
}
