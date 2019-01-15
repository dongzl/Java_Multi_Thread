package com.multi.thread.guide.core.chapter3.section9;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/15 上午11:34
 */
public class EnumBasedSingletonExample {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread() {
            @Override
            public void run() {
                Singleton.INSTANCE.someService();
            }
        };
    }

    public static enum Singleton {
        INSTANCE;

        Singleton() {

        }

        public void someService() {

        }
    }
}
