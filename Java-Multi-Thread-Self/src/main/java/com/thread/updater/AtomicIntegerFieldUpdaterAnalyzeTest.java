package com.thread.updater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/10 下午5:17
 */
public class AtomicIntegerFieldUpdaterAnalyzeTest {

    public static void main(String[] args) throws Exception {
        AtomicIntegerFieldUpdaterAnalyzeTest test = new AtomicIntegerFieldUpdaterAnalyzeTest();
        test.testValue();
    }

    public AtomicIntegerFieldUpdater<DataDemo> updater(String name) {
        return AtomicIntegerFieldUpdater.newUpdater(DataDemo.class, name);
    }

    public void testValue() {
        DataDemo data = new DataDemo();
        //访问父类的public 变量，报错：java.lang.NoSuchFieldException
//        System.out.println("fatherVar = " + updater("fatherVar").getAndIncrement(data));

        //访问普通 变量，报错：java.lang.IllegalArgumentException: Must be volatile type
//        System.out.println("intVar = " + updater("intVar").getAndIncrement(data));

        //访问public volatile int 变量，成功
        System.out.println("publicVar = " + updater("publicVar").incrementAndGet(data));

        //访问protected volatile int 变量，成功
        System.out.println("protectedVar = " + updater("protectedVar").incrementAndGet(data));

        //访问其他类private volatile int变量，失败：java.lang.IllegalAccessException
//        System.out.println("privateVar = " + updater("privateVar").getAndIncrement(data));

        //访问，static volatile int，失败，只能访问实例对象：java.lang.IllegalArgumentException
//        System.out.println("staticVar = " + updater("staticVar").getAndIncrement(data));

        //访问integer变量，失败， Must be integer type
//        System.out.println("integerVar = " + updater("integerVar").getAndIncrement(data));

        //访问long 变量，失败， Must be integer type
//        System.out.println("longVar = " + updater("longVar").getAndIncrement(data));


        data.testPrivate();
    }

    static class Father {
        public volatile int fatherVar = 4;
    }

    static class DataDemo extends Father {

        public int intVar = 4;

        public volatile int publicVar = 3;

        protected volatile int protectedVar = 4;

        private volatile int privateVar = 5;

        public volatile static int staticVar = 10;

//        public final volatile int finalVar = 11;

        public volatile Integer integerVar = 18;

        public volatile Long longVar = 19L;

        public void testPrivate() {
            DataDemo data = new DataDemo();
            System.out.println(AtomicIntegerFieldUpdater.newUpdater(DataDemo.class, "privateVar").incrementAndGet(data));
        }
    }
}
