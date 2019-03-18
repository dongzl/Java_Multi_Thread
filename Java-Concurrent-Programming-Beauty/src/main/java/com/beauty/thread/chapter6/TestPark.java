package com.beauty.thread.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * @author dongzonglei
 * @description
 * @date 2019-03-18 11:07
 */
public class TestPark {

    public void testPark() {
        LockSupport.park(this);
//        LockSupport.park();
    }

    public static void main(String args[]) throws Exception {
        TestPark testPark = new TestPark();
        testPark.testPark();
    }
}
