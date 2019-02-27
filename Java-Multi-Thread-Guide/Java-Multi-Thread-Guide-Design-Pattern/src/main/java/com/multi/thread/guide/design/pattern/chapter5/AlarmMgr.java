package com.multi.thread.guide.design.pattern.chapter5;

import com.multi.thread.guide.design.pattern.chapter4.AlarmAgent;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-22 16:51
 */
public class AlarmMgr {

    private static final AlarmMgr INSTANCE = new AlarmMgr();

    private volatile boolean shutdownRequested = false;

    private final AlarmSendingThread alarmSendingThread;

    private AlarmMgr(AlarmSendingThread alarmSendingThread) {
        this.alarmSendingThread = alarmSendingThread;
    }

    public static AlarmMgr getInstance() {
        return INSTANCE;
    }

    public int sendAlarm(AlarmType type, String id, String extraInfo) {
        System.out.println("Trigger alarm " + type + "," + id + "," + extraInfo);
        int duplicateSubmissionCount = 0;
        try {
            AlarmInfo alarmInfo = new AlarmAgent.AlarmInfo();
            alarmInfo.set

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void
}
