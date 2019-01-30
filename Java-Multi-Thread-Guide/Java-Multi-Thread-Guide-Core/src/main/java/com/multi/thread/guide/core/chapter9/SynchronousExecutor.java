package com.multi.thread.guide.core.chapter9;

import java.util.concurrent.Executor;

/**
 * @author dongzonglei
 * @description
 * @date 2019-01-30 17:21
 */
public class SynchronousExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
