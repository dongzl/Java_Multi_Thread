package com.multi.thread.guide.core.chapter12;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-13 11:21
 */
public class LogPrinterV2 {

    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyMMddHHmm");

    final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00");

    final static int MAX_RECORDS_PRE_FILE = 10_000;

    private PrintWriter pwr = null;

    private int recordsInFile = MAX_RECORDS_PRE_FILE;

    private int fileSeq = 0;

    public void print(String record) {
        PrintWriter writer;
        try {
            synchronized (this) {
                writer = getPrintWriter();
                writer.println(record);
                recordsInFile++;
            }
        } catch (Exception e) {

        }
    }

    public synchronized void shutdown() {
        if (null != pwr) {
            pwr.close();
            pwr = null;
        }
    }

    private PrintWriter getPrintWriter() throws Exception {
        PrintWriter writer = null;
        if (MAX_RECORDS_PRE_FILE == recordsInFile) {
            String newFileName = retrieveFileName();
            writer = new PrintWriter(newFileName){
                {
                    lock = this;
                }
            };
            recordsInFile = 0;
            if (null != pwr) {
                pwr.flush();
                pwr.close();
            }
            pwr = writer;
        } else {
            writer = pwr;
        }
        return writer;
    }

    protected String retrieveFileName() {
        String fileName;
        fileName = "/home/viscent/tmp/logs/" + DATE_FORMAT.format(new Date()) + DECIMAL_FORMAT.format(fileSeq) + ".log";
        if (++fileSeq > 99) {
            fileSeq = 0;
        }
        return fileName;
    }
}
