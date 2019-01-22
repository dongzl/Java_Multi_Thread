package com.multi.thread.guide.core.chapter5.section7;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/22 上午10:40
 */
public class ThreadManagementContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ThreadTerminationRegistry.INSTANCE.clearThreads();
    }

    static abstract class AbstractMonitorThread extends Thread {
        private final long interval;

        final AtomicBoolean terminationToken = new AtomicBoolean(false);

        public AbstractMonitorThread(long interval) {
            this.interval = interval;
            this.setDaemon(true);
            ThreadTerminationRegistry.Handler handler;
            handler = new ThreadTerminationRegistry.Handler() {
                @Override
                public void terminate() {
                    terminationToken.set(true);
                    AbstractMonitorThread.this.interrupt();
                }
            };
            ThreadTerminationRegistry.INSTANCE.register(handler);
        }

        @Override
        public void run() {
            try {
                while (!terminationToken.get()) {
                    doMonitor();
                    Thread.sleep(interval);
                }
            } catch (Exception e) {

            }
            System.out.println("terminated:" + Thread.currentThread());
        }

        protected abstract void doMonitor();
    }
}
