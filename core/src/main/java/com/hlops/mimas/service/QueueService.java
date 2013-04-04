package com.hlops.mimas.service;

import com.hlops.mimas.data.EntityKey;
import com.hlops.mimas.sync.CallableTask;

import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 31.03.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public interface QueueService {

    <T> Future<T> getFuture(CallableTask<? extends EntityKey, T> callableTask);
}

