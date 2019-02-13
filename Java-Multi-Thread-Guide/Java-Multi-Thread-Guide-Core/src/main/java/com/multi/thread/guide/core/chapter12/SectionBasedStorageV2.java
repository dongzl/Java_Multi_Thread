package com.multi.thread.guide.core.chapter12;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-13 11:44
 */
public class SectionBasedStorageV2 {

    private Deque<String> sectionNames = new LinkedList<>();

    private Map<String, AtomicInteger> sectionFileCountMap = new HashMap<>();

    private int maxFilesPreSection = 2000;

    private int maxSectionCount = 100;

    private String storageBaseDir = System.getProperty("java.io.tmpdir") + "/vpn";

    public SectionBasedStorageV2() {
        File dir = new File(storageBaseDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public synchronized String[] apply4Filename() {
        String sectionName;
        int iFileCount;
        String[] fileName = new String[2];
        String oldestSectionName = null;
        synchronized (this) {
            sectionName = getSectionName();
            AtomicInteger fileCount;
            fileCount = sectionFileCountMap.get(sectionName);
            iFileCount = fileCount.get();
            if (iFileCount >= maxFilesPreSection) {
                if (sectionNames.size() >= maxSectionCount) {
                    oldestSectionName = sectionNames.removeFirst();
                    removeSection(oldestSectionName);
                }
                sectionName = makeNewSectionDir();
                fileCount = sectionFileCountMap.get(sectionName);
            }
            iFileCount = fileCount.incrementAndGet();
        }
        fileName[0] = storageBaseDir + "/" + sectionName + "/" + new DecimalFormat("0000").format(iFileCount) + "-" + new Date().getTime() / 1000 + ".rq";
        fileName[1] = sectionName;
        return fileName;
    }

    private String getSectionName() {
        String sectionName;
        if (sectionNames.isEmpty()) {
            sectionName = makeNewSectionDir();
        } else {
            sectionName = sectionNames.getLast();
        }
        return sectionName;
    }

    private String makeNewSectionDir() {
        String sectionName;
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        sectionName = sdf.format(new Date());
        File dir = new File(storageBaseDir + "/" + sectionName);
        if (dir.mkdir()) {
            sectionNames.addLast(sectionName);
            sectionFileCountMap.put(sectionName, new AtomicInteger(0));
        } else {
            throw new RuntimeException("Cannot create section dir" + sectionName);
        }
        return sectionName;
    }


    private boolean removeSection(String sectionName) {
        boolean result = true;
        File dir = new File(storageBaseDir + "/" + sectionName);
        for (File file : dir.listFiles()) {
            result = result && file.delete();
        }
        result = result && dir.delete();
        return result;
    }

    public void decrementSectionFileCount(String sectionName) {
        AtomicInteger fileCount = sectionFileCountMap.get(sectionName);
        if (null != fileCount) {
            fileCount.decrementAndGet();
        }
    }


}
