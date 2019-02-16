package com.multi.thread.guide.design.pattern.chapter4;

import java.util.concurrent.Callable;

/**
 * @author dongzonglei
 * @description
 * @date 2019-02-16 11:16
 */
public interface Blocker {

    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void signal() throws Exception;

    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
