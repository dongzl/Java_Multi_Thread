package com.multi.thread.guide.core.chapter8;

import com.multi.thread.guide.core.Tools;

import java.util.Scanner;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/28 上午9:49
 */
public class ThreadPauseDemo {

    final static PauseControl pc = new PauseControl();

    public static void main(String[] args) throws Exception {
        final Runnable action = new Runnable() {
            @Override
            public void run() {
                System.out.println();
                Tools.randomPause(300);
            }
        };
        Thread slave = new Thread() {
            @Override
            public void run() {
                try {
                    for (;;) {
                        pc.pauseIfNecessary(action);
                    }
                } catch (Exception e) {

                }
            }
        };
        slave.setDaemon(true);
        slave.start();
        askOnBehaveOfSlave();
    }

    private static void askOnBehaveOfSlave() {
        String answer;
        int minPause = 2000;
        try(Scanner sc = new Scanner(System.in)) {
            for (;;) {
                Tools.randomPause(8000);
                pc.requestPause();
                answer = sc.next();
                if ("1".equals(answer)) {
                    pc.requestPause();
                    minPause = 8000;
                } else if ("2".equals(answer)) {
                    pc.proceed();
                    minPause = 2000;
                } else {
                    break;
                }
            }
        }
    }
}
