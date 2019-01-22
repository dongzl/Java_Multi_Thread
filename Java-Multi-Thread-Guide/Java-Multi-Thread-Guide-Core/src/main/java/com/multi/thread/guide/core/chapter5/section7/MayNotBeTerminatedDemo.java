package com.multi.thread.guide.core.chapter5.section7;

import com.multi.thread.guide.core.chapter5.section5.TaskRunner;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/22 上午9:49
 */
public class MayNotBeTerminatedDemo {

    public static void main(String[] args) throws Exception {
        TaskRunner tr = new TaskRunner();
        tr.init();
        tr.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("before doing task");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println("after doing task");
            }
        });
        //tr.workThread.interrupt();
    }
}
