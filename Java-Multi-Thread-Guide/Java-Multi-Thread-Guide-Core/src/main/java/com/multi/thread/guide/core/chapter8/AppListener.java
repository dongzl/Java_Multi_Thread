package com.multi.thread.guide.core.chapter8;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/27 下午6:53
 */
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread.UncaughtExceptionHandler ueh = new LoggingUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(ueh);
        startService();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Thread.setDefaultUncaughtExceptionHandler(null);
        stopService();
    }

    static class LoggingUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {

        }
    }

    protected void startService() {

    }

     protected void stopService() {

     }
}
