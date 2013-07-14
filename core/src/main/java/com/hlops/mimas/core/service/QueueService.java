package com.hlops.mimas.core.service;

import com.hlops.mimas.core.sync.CallableTask;
import com.hlops.mimas.core.sync.RunnableTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 31.03.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public interface QueueService {

    <T> Future<T> getFuture(CallableTask<T> callableTask);

    void waitFuture(RunnableTask callableTask) throws ExecutionException, InterruptedException;

    void incrementPoolSize();

    void decrementPoolSize();
}

