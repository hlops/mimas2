package com.hlops.mimas.service.impl;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.EntityKey;
import com.hlops.mimas.data.KeyProvider;
import com.hlops.mimas.data.TaskKey;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.sync.AbstractTask;
import com.hlops.mimas.sync.CallableTask;
import com.hlops.mimas.sync.RunnableTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final AtomicInteger poolSize = new AtomicInteger();

    private static Logger logger = LoggerFactory.getLogger(QueueService.class);

    public QueueServiceImpl() {
        poolSize.set(MimasConfig.getInstance().getQueueExecutors());
        threadExecutor = new ThreadPoolExecutor(poolSize.intValue(), poolSize.intValue(), 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                logger.debug("before executing thread {} runnable {}", t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                KeyFutureTask task = (KeyFutureTask) r;
                logger.debug("after executing runnable {}, Throwable {}", r, t);
                if (task.isWaitingTask) {
                    applyPoolSize(poolSize.decrementAndGet(), false);
                }
            }
        };
    }

    public <T> Future<T> getFuture(CallableTask<T> callableTask) {
        EntityKey key = callableTask.getKey();
        @SuppressWarnings("unchecked") Future<T> result = syncMap.get(key);
        if (result != null) {
            logger.debug("getFuture already exists ({})", callableTask.getKey());
            return result;
        }

        FutureTask<T> newFuture = new KeyFutureTask<T>(callableTask, false);
        //noinspection unchecked
        result = (Future<T>) syncMap.putIfAbsent(key, newFuture);
        if (result == null) {
            threadExecutor.execute(newFuture);
            result = newFuture;
            logger.debug("getFuture was added to queue ({})", callableTask.getKey());
        }
        return result;
    }

    public void waitFuture(RunnableTask runnableTask) throws ExecutionException, InterruptedException {
        EntityKey key = runnableTask.getKey();
        @SuppressWarnings("unchecked") Future result = syncMap.get(key);
        if (result == null) {
            FutureTask newFuture = new KeyFutureTask(runnableTask, true);
            //noinspection unchecked
            result = syncMap.putIfAbsent(key, newFuture);
            if (result == null) {
                applyPoolSize(poolSize.incrementAndGet(), true);
                threadExecutor.execute(newFuture);
                result = newFuture;
                logger.debug("waitFuture was added to queue ({})", runnableTask.getKey());
            }
        }
        result.get();
    }

    @Override
    public void incrementPoolSize() {
        applyPoolSize(poolSize.incrementAndGet(), true);
    }

    @Override
    public void decrementPoolSize() {
        applyPoolSize(poolSize.decrementAndGet(), false);
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

        private final boolean isWaitingTask;
        private final TaskKey key;
        private final AbstractTask task;

        public KeyFutureTask(CallableTask<T> callable, boolean waitingTask) {
            super(callable);
            isWaitingTask = waitingTask;
            this.key = callable.getKey();
            this.task = callable;
        }

        public KeyFutureTask(RunnableTask runnable, boolean waitingTask) {
            super(runnable, null);
            isWaitingTask = waitingTask;
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

        public boolean isWaitingTask() {
            return isWaitingTask;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }
}
