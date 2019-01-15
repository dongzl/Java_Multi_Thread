package com.multi.thread.guide.core.chapter3.section9;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午11:26
 */
public class StaticHolderSingleton {

    private StaticHolderSingleton() {

    }

    private static class InstanceHolder {
        final static StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
    }

    public static StaticHolderSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void someService() {

    }

    public static void main(String[] args) throws Exception {
        StaticHolderSingleton.getInstance().someService();
    }
}
