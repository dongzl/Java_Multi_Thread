package com.multi.thread.guide.core.chapter1.section8;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/11 下午3:30
 */
public class FileDownloaderApp {

    public static void main(String[] args) throws Exception {
        Thread downloadThread = null;
        for (String url : args) {
            downloadThread = new Thread(new FileDownloader(url));
            downloadThread.start();
        }
    }

    static class FileDownloader implements Runnable {

        private final String fileUrl;

        public FileDownloader(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        @Override
        public void run() {
            String fileBaseName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
            try {
                URL url = new URL(fileUrl);
                String localFileName = System.getProperty("java.io.tmpdir") + "/viscent-" + fileBaseName;
                downloadFile(url, new FileOutputStream(localFileName), 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void downloadFile(URL url, OutputStream outputStream, int bufSize) {

        }
    }
}
