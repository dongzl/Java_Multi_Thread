package com.multi.thread.guide.core.chapter9;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * @author dongzonglei
 * @description
 * @date 2019-01-31 11:57
 */
public abstract class AsyncTask<V> implements Runnable, Callable<V> {

    protected final Executor executor;

    public AsyncTask(Executor executor) {
        this.executor = executor;
    }

    public AsyncTask() {
        this(new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        });
    }

    @Override
    public void run() {
        Exception exp = null;
        V r = null;
        try {
            r = call();
        } catch (Exception e) {
            exp = e;
        }
        final V result = r;
        if (null == exp) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    onResult(result);
                }
            });
        } else {
            final Exception exceptionCaught = exp;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    onError(exceptionCaught);
                }
            });
        }
    }

    protected abstract void onResult(V result);

    protected void onError(Exception e) {
        e.printStackTrace();
    }
}
