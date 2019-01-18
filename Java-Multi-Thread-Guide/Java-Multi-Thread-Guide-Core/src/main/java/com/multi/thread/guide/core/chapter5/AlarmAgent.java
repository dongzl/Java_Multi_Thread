package com.multi.thread.guide.core.chapter5;

import com.multi.thread.guide.core.Tools;

import java.util.Random;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/18 上午9:42
 */
public class AlarmAgent {

    private final static AlarmAgent INSTANCE = new AlarmAgent();

    private boolean connectToServer = false;

    private final HeartBeartThread heartBeartThread = new HeartBeartThread();

    private AlarmAgent() {

    }

    public static AlarmAgent getInstance() {
        return INSTANCE;
    }

    public void init() {
        connectToServer();
        heartBeartThread.setDaemon(false);
        heartBeartThread.start();
    }

    private void connectToServer() {
        new Thread() {
            @Override
            public void run() {
                doConnect();
            }
        }.start();
    }

    private void doConnect() {
        Tools.randomPause(100);
        synchronized (this) {
            connectToServer = true;
            notify();
        }
    }

    public void sendAlarm(String message) throws InterruptedException {
        synchronized (this) {
            while (!connectToServer) {
                System.out.println("Alarm agent was not connect to server.");
                wait();
            }
        }
        doSendAlarm(message);
    }

    private void doSendAlarm(String message) {
        System.out.println("Alarm send:" + message);
    }

    class HeartBeartThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                while (true) {
                    if (checkConnection()) {
                        connectToServer = true;
                    } else {
                        connectToServer = false;
                        System.out.println("Alarm agent was not disConnected to server.");
                        connectToServer();
                    }
                    Thread.sleep(2000);
                 }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private boolean checkConnection() {
            boolean isConnected = true;
            final Random random = new Random();
            int rand = random.nextInt(1000);
            if (rand < 500) {
                isConnected = false;
            }
            return isConnected;
        }
    }
}
