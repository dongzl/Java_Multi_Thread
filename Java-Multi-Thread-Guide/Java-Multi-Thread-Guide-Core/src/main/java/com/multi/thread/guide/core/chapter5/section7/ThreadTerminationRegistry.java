package com.multi.thread.guide.core.chapter5.section7;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/22 上午10:31
 */
public enum ThreadTerminationRegistry {

    INSTANCE;

    private final Set<Handler> handlers = new HashSet<>();

    public synchronized void register(Handler handler) {
        handlers.add(handler);
    }

    public void clearThreads() {
        final Set<Handler> handlersSnapshot;
        synchronized (this) {
            handlersSnapshot = new HashSet<>(handlers);
        }
        for (Handler handler : handlersSnapshot) {
            try {
                handler.terminate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static interface Handler {

        void terminate();
    }
}
