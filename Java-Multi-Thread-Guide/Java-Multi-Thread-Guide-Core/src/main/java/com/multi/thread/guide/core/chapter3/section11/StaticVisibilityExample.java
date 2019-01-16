package com.multi.thread.guide.core.chapter3.section11;

import com.multi.thread.guide.core.Tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/16 上午9:22
 */
public class StaticVisibilityExample {

    private static Map<String, String> taskConfig;

    static {
        System.out.println("The class being initialized...");
        taskConfig = new HashMap<>();
        taskConfig.put("url", "https://github.com/Viscent");
        taskConfig.put("timeout", "1000");
    }

    public static void changeConfig(String url, String timeout) {
        taskConfig = new HashMap<>();
        taskConfig.put("url", url);
        taskConfig.put("timeout", timeout);
    }

    public static void init() {
        Thread t = new Thread() {
            @Override
            public void run() {
                String url = taskConfig.get("url");
                String timeout = taskConfig.get("timeout");
                doTask(url, Integer.valueOf(timeout));
            }
        };
        t.start();
    }

    private static void doTask(String url, int timeout) {
        Tools.randomPause(500);
    }
}
