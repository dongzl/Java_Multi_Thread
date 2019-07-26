package com.multi.thread.guide.design.pattern.chapter4;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-16 11:13
 */
public class AlarmAgent {

    // 用于记录AlarmAgent是否连接上告警服务器
    private volatile boolean connectedToServer = false;

    // 模式角色：GuardedSuspension.Predicate
    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    // 模式角色：GuardedSuspension.Blocker
    private final Blocker blocker = new ConditionVarBlocker();

    // 心跳定时器
    private final Timer heatbeatTimer = new Timer(true);

    /**
     * 发送告警信息
     * @param alarm 告警信息
     * @throws Exception
     */
    public void sendAlarm(final AlarmInfo alarm) throws Exception {
        // 可能需要等待，直到AlarmAgent连接上告警服务器（或者连接中断后重新连上服务器）
        // 模式角色：GuardedSuspension.GuardedAction
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    // 通过网络连接将告警信息发送到告警服务器
    private void doSendAlarm(AlarmInfo alarm) {
        System.out.println("sending alarm" + alarm);
        try {
            Thread.sleep(50);
        } catch (Exception e) {

        }
    }

    public void init() {
        Thread connectingThread = new Thread(new ConnectingTask());
        connectingThread.start();
        heatbeatTimer.schedule(new HeartbeatTask(), 60000, 2000);
    }

    public void disconnect() {
        System.out.println("disconnected from alarm server");
        connectedToServer = false;
    }

    protected void onConnected() {
        try {
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    connectedToServer = true;
                    System.out.println("connected to server");
                    return Boolean.TRUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDisconnected() {
        connectedToServer = false;
    }

    // 负责与告警服务器建立连接
    private class ConnectingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                ;
            }
            onConnected();
        }
    }

    /**
     * 心跳定时检查：定时检查与告警服务器的连接是否正常，发现连接异常后自动重新连接
     */
    private class HeartbeatTask extends TimerTask {
        @Override
        public void run() {
            if (!testConnection()) {
                onDisconnected();
                reconnect();
            }
        }

        private boolean testConnection() {
            return true;
        }

        private void reconnect() {
            ConnectingTask connectingThread = new ConnectingTask();
            connectingThread.run();
        }
    }

    private class AlarmInfo {
        
    }
}
