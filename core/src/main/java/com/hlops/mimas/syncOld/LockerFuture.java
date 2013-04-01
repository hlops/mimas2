package com.hlops.mimas.syncOld;

import java.util.concurrent.RunnableFuture;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/30/13
 * Time: 4:43 PM
 */
public interface LockerFuture<Result, Lock> extends RunnableFuture<Result> {

    Lock getLock();

    LockPolicy getLockPolicy();

    void setDoneHandler(DoneHandler handler);
}
