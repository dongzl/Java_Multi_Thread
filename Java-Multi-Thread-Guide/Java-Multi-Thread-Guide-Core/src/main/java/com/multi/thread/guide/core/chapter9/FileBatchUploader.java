package com.multi.thread.guide.core.chapter9;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author dongzonglei
 * @description
 * @date 2019-01-31 09:39
 */
public class FileBatchUploader implements Closeable {

    private final CompletionService<File> completionService;

    private final ExecutorService es;

    private final ExecutorService dispatcher;

    public FileBatchUploader(String ftpServer, String userName, String password, String targetRemoteDir) {
        this.es = Executors.newSingleThreadExecutor();
        this.dispatcher = Executors.newSingleThreadExecutor();
        this.completionService = new ExecutorCompletionService<>(es);
    }

    public void uploadFiles(final Set<File> files) {
        dispatcher.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    doUploadFiles(files);
                } catch (Exception e) {

                }
            }
        });
    }

    private void doUploadFiles(Set<File> files) throws InterruptedException {
        for (final File file : files) {
            completionService.submit(new UploadTask(file));
        }
        Future<File> future;
        File md5File;
        File uploadedFile;
        Set<File> md5Files = new HashSet<>();
        for (File file : files) {
            try {
                future = completionService.take();
                uploadedFile = future.get();
                md5File = generateMD5(moveToSuccessDir(uploadedFile));
                md5Files.add(md5File);
            } catch (Exception e) {
                e.printStackTrace();
                moveToDeadDir(file);
            }
        }
        for (File file : files) {
            completionService.submit(new UploadTask(file));
        }
        int successUploaded = md5Files.size();
        for (int i = 0; i < successUploaded; i++) {
            future = completionService.take();
            try {
                uploadedFile = future.get();
                md5Files.remove(uploadedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (File file : md5Files) {
            moveToDeadDir(file);
        }
    }

    private File generateMD5(File file) throws Exception {
        System.out.println("");
        String md5 = null;
        File md5File = new File(file.getAbsolutePath() + ".md5");
        Files.write(Paths.get(md5File.getAbsolutePath()), md5.getBytes("UTF-8"));
        return md5File;
    }

    private static File moveToDeadDir(File file) {
        File targetFile = null;
        try {
            targetFile = moveFile(file, Paths.get(file.getParent(), "..", "backup", "dead"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetFile;
    }

    private static File moveToSuccessDir(File file) {
        File targetFile = null;
        try {
            targetFile = moveFile(file, Paths.get(file.getParent(), "..", "backup", "success"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetFile;
    }

    private static File moveFile(File file, Path path) throws Exception {
        return null;
    }

    class UploadTask implements Callable<File> {

        private final File file;

        public UploadTask(File file) {
            this.file = file;
        }

        @Override
        public File call() throws Exception {
            upload(file);
            return file;
        }
    }

    public void init() throws Exception {

    }

    public void upload(File file) throws Exception {

    }

    @Override
    public void close() throws IOException {

    }

    public static void main(String args[]) throws Exception {
        
    }
}
