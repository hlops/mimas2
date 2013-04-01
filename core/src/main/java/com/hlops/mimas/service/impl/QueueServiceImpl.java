package com.hlops.mimas.service.impl;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.syncOld.LockerFuture;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 31.03.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class QueueServiceImpl implements QueueService {

    private final ThreadPoolExecutor threadExecutor;
    private final ConcurrentHashMap<Object, LockerFuture> lockers = new ConcurrentHashMap<Object, LockerFuture>();

    public QueueServiceImpl() {
        int executors = MimasConfig.getInstance().getQueueExecutors();
        threadExecutor = new ThreadPoolExecutor(executors, executors, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }
}
