package com.multi.thread.guide.core.chapter8;

import com.multi.thread.guide.core.chapter4.section3.BigFileDownloader;
import com.multi.thread.guide.core.chapter4.section3.DownloadTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/28 上午11:50
 */
public class TPBigFileDownloader extends BigFileDownloader {

    final static int N_CPU = Runtime.getRuntime().availableProcessors();

    final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, N_CPU * 2, 4,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(N_CPU * 8), new ThreadPoolExecutor.CallerRunsPolicy());

    public TPBigFileDownloader(String file) throws Exception {
        super(file);
    }

    public static void main(String[] args) throws Exception {
        final int argc = args.length;
        TPBigFileDownloader downloader = new TPBigFileDownloader(args[0]);
        long reportInterval = argc >= 2 ? Integer.valueOf(args[1]) : 10;
        final int taskCount = N_CPU * 8;
        downloader.download(taskCount, reportInterval * 1000);
    }

    @Override
    protected void dispatchWork(DownloadTask dt, int workerIndex) {
        executor.submit(new Runnable() {
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
    }

    @Override
    protected void doCleanup() {
        executor.shutdownNow();
        super.doCleanup();
    }
}
