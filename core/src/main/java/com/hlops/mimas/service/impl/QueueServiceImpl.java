package com.hlops.mimas.service.impl;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.EntityKey;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.sync.CallableTask;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 31.03.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class QueueServiceImpl implements QueueService {

    private final ThreadPoolExecutor threadExecutor;
    private final ConcurrentHashMap<EntityKey, Future> syncMap = new ConcurrentHashMap<EntityKey, Future>();

    public QueueServiceImpl() {
        int executors = MimasConfig.getInstance().getQueueExecutors();
        executors = 5;
        threadExecutor = new ThreadPoolExecutor(executors, executors, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        threadExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public <T> Future<T> getFuture(CallableTask<? extends EntityKey, T> callableTask) {
/*
        System.out.println(threadExecutor.getTaskCount() + "\t" +
                threadExecutor.getActiveCount() + "\t" +
                threadExecutor.getCompletedTaskCount() + "\t" +
                threadExecutor.getTaskCount() + "\t"
        );
*/
        EntityKey key = callableTask.getKey();
        @SuppressWarnings("unchecked") Future<T> result = syncMap.get(key);
        if (result != null) {
            return result;
/*
            if (result.isDone()) {
                syncMap.remove(key, result);
            } else {
                return result;
            }
*/
        }

        FutureTask<T> newFuture = new FutureTask<T>(callableTask);
        //noinspection unchecked
        result = (Future<T>) syncMap.putIfAbsent(key, newFuture);
        if (result == null) {
            threadExecutor.execute(newFuture);
            result = newFuture;
        }
        return result;
    }

}
