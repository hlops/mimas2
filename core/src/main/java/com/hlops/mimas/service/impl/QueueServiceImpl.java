package com.hlops.mimas.service.impl;

import com.hlops.mimas.config.MimasConfig;
import com.hlops.mimas.data.EntityKey;
import com.hlops.mimas.data.KeyProvider;
import com.hlops.mimas.data.TaskKey;
import com.hlops.mimas.service.QueueService;
import com.hlops.mimas.sync.AbstractTask;
import com.hlops.mimas.sync.CallableTask;
import com.hlops.mimas.sync.RunnableTask;

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

    public QueueServiceImpl() {
        poolSize.set(MimasConfig.getInstance().getQueueExecutors());
        // poolSize.set(50);
        threadExecutor = new ThreadPoolExecutor(poolSize.intValue(), poolSize.intValue(), 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                KeyFutureTask task = (KeyFutureTask) r;
                System.out.println("remove " + syncMap.remove(task.getKey()));
                if (task.isWaitingTask) {
                    applyPoolSize(poolSize.decrementAndGet());
                }
            }
        };
    }

    public <T> Future<T> getFuture(CallableTask<T> callableTask) {
        EntityKey key = callableTask.getKey();
        @SuppressWarnings("unchecked") Future<T> result = syncMap.get(key);
        if (result != null) {
            return result;
        }

        FutureTask<T> newFuture = new KeyFutureTask<T>(callableTask, false);
        //noinspection unchecked
        result = (Future<T>) syncMap.putIfAbsent(key, newFuture);
        if (result == null) {
            threadExecutor.execute(newFuture);
            result = newFuture;
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
                applyPoolSize(poolSize.incrementAndGet());
                threadExecutor.execute(newFuture);
                result = newFuture;
            }
        }
        System.out.println("waiting");
        result.get();
    }

    private synchronized void applyPoolSize(int n) {
        if (n > threadExecutor.getCorePoolSize()) {
            threadExecutor.setCorePoolSize(n);
            threadExecutor.setMaximumPoolSize(n);
            System.out.println("applyPoolSize: " + n + " " + threadExecutor.getCorePoolSize() + " " + threadExecutor.getActiveCount() + " " + threadExecutor.prestartAllCoreThreads());
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
    }
}
