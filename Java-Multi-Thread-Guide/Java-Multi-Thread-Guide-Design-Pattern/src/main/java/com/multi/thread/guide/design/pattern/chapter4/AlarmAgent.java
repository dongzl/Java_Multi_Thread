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

    private volatile boolean connectedToServer = false;

    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    private final Blocker blocker = new ConditionVarBlocker();

    private final Timer heatbeatTimer = new Timer(true);

    public void sendAlarm(final AlarmInfo alarm) throws Exception {
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

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
