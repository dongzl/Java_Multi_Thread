package com.beauty.thread.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-14 20:35
 */
public class TestUnSafe2 {

    static final Unsafe unsafe;

    static final long startOffset;

    private volatile long state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            startOffset = unsafe.objectFieldOffset(TestUnSafe2.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String args[]) throws Exception {
        TestUnSafe2 test = new TestUnSafe2();
        Boolean success = unsafe.compareAndSwapInt(test, startOffset,0 ,1);
        System.out.println(success);
        System.out.println(test.state);
    }
}
