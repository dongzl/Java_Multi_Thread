package com.beauty.thread.chapter2;

import sun.misc.Unsafe;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-14 20:35
 */
public class TestUnSafe {

    static final Unsafe unsafe = Unsafe.getUnsafe();

    static final long startOffset;

    private volatile long state = 0;

    static {
        try {
            startOffset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String args[]) throws Exception {
        TestUnSafe test = new TestUnSafe();
        Boolean success = unsafe.compareAndSwapInt(test, startOffset,0 ,1);
        System.out.println(success);
    }
}
