package com.hlops.mimas.core.service.impl;

import com.hlops.mimas.core.Mimas;
import com.hlops.mimas.core.data.EntityKey;
import com.hlops.mimas.core.data.KeyProvider;
import com.hlops.mimas.core.data.TaskKey;
import com.hlops.mimas.core.service.QueueService;
import com.hlops.mimas.core.sync.AbstractTask;
import com.hlops.mimas.core.sync.CallableTask;
import com.hlops.mimas.core.sync.RunnableTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    private final AtomicInteger poolSize = new AtomicInteger(Mimas.getConfig().getSyncConfig().getQueueExecutors());

    private static Logger logger = LoggerFactory.getLogger(QueueService.class);

    public QueueServiceImpl() {
        threadExecutor = new ThreadPoolExecutor(poolSize.intValue(), poolSize.intValue(), 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                logger.debug("before executing thread {} runnable {}", t, r);
                KeyFutureTask task = (KeyFutureTask) r;
                if (task.getTask().isBlocking()) {
                    applyPoolSize(poolSize.incrementAndGet(), true);
                }
                task.startTime = System.currentTimeMillis();
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                KeyFutureTask task = (KeyFutureTask) r;
                logger.debug("after executing runnable {}, Throwable {}", r, t);
                if (task.getTask().isBlocking()) {
                    applyPoolSize(poolSize.decrementAndGet(), false);
                }
                task.finishedTime = System.currentTimeMillis();
                TaskKey key = task.getKey();
                if (key.getTimeout() <= 0) {
                    syncMap.remove(key);
                }
            }
        };
    }

    @Override
    public void dispose() {
        threadExecutor.shutdown();
    }

    private void removeObsoleteTasks() {
        for (Map.Entry<EntityKey, Future> entry : syncMap.entrySet()) {
            KeyFutureTask task = (KeyFutureTask) entry.getValue();
            if (task.finishedTime > 0 && task.finishedTime + task.getKey().getTimeout() <= System.currentTimeMillis()) {
                syncMap.remove(entry.getKey(), entry.getValue());
            }
        }
    }

    public <T> Future<T> getFuture(CallableTask<T> callableTask) {
        removeObsoleteTasks();
        EntityKey key = callableTask.getKey();
        @SuppressWarnings("unchecked") Future<T> result = syncMap.get(key);
        if (result == null) {
            FutureTask<T> newFuture = new KeyFutureTask<T>(callableTask);
            //noinspection unchecked
            result = (Future<T>) syncMap.putIfAbsent(key, newFuture);
            if (result == null) {
                threadExecutor.execute(newFuture);
                result = newFuture;
                logger.debug("New callable was added to queue ({})", callableTask.getKey());
            }
        }
        return result;
    }

    public Future getFuture(RunnableTask runnableTask) {
        removeObsoleteTasks();
        EntityKey key = runnableTask.getKey();
        @SuppressWarnings("unchecked") Future result = syncMap.get(key);
        if (result == null) {
            FutureTask newFuture = new KeyFutureTask(runnableTask);
            //noinspection unchecked
            result = syncMap.putIfAbsent(key, newFuture);
            if (result == null) {
                threadExecutor.execute(newFuture);
                result = newFuture;
                logger.debug("New runnable was added to queue ({})", runnableTask.getKey());
            }
        }
        return result;
    }

    private synchronized void applyPoolSize(int n, boolean isIncrementing) {
        if ((isIncrementing && n > threadExecutor.getCorePoolSize()) || (!isIncrementing && n < threadExecutor.getCorePoolSize())) {
            threadExecutor.setCorePoolSize(n);
            threadExecutor.setMaximumPoolSize(n);
            logger.debug("applyPoolSize {} {}", threadExecutor.getCorePoolSize(), n);
        } else {
            logger.debug("not applyPoolSize {} {} {}", new Object[]{threadExecutor.getCorePoolSize(), n, isIncrementing});
        }
    }

    class KeyFutureTask<T> extends FutureTask<T> implements KeyProvider<TaskKey> {

        private final TaskKey key;
        private final AbstractTask task;

        private long startTime;
        private long finishedTime;

        public KeyFutureTask(CallableTask<T> callable) {
            super(callable);
            this.key = callable.getKey();
            this.task = callable;
        }

        public KeyFutureTask(RunnableTask runnable) {
            super(runnable, null);
            this.key = runnable.getKey();
            this.task = runnable;
        }

        @Override
        public TaskKey getKey() {
            return key;
        }

        public AbstractTask getTask() {
            return task;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }
}
