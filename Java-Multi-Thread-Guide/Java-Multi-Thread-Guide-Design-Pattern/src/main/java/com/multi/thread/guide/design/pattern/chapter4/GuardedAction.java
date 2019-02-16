package com.multi.thread.guide.design.pattern.chapter4;

import java.util.concurrent.Callable;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-16 11:15
 */
public abstract class GuardedAction<V> implements Callable<V> {

    protected final Predicate guard;

    public GuardedAction(Predicate guard) {
        this.guard = guard;
    }
}
