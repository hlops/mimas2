package com.hlops.mimas.service.impl;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.EntityKey;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.sync.EntityKeyFuture;
import com.hlops.mimas.sync.TaskFactory;

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
    private final ConcurrentHashMap<Object, EntityKeyFuture> sync = new ConcurrentHashMap<Object, EntityKeyFuture>();

    public QueueServiceImpl() {
        int executors = MimasConfig.getInstance().getQueueExecutors();
        threadExecutor = new ThreadPoolExecutor(executors, executors, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        threadExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public <T extends EntityKeyFuture> T get(EntityKey key, TaskFactory<T> factory) {
        @SuppressWarnings("unchecked") T result = (T) sync.get(key);
        if (result != null) {
            if (result.isDone()) {
                sync.remove(key, result);
            } else {
                return result;
            }
        }

        T newFuture;
        //noinspection unchecked
        result = (T) sync.putIfAbsent(key, newFuture = factory.create(key));
        if (result == null) {
            result = newFuture;
            threadExecutor.execute(result);
        }
        return result;
    }
}
