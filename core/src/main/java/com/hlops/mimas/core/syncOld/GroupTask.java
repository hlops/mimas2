package com.hlops.mimas.core.syncOld;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 13.02.13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public abstract class GroupTask implements Future<Boolean> {

    private final List<RunnableFuture> tasks;

    protected GroupTask(@NotNull List<RunnableFuture> tasks) {
        this.tasks = tasks;
    }

    @NotNull
    public List<RunnableFuture> getTasks() {
        return tasks;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean result = true;
        for (RunnableFuture future : tasks) {
            result = future.cancel(mayInterruptIfRunning) && result;
        }
        return result;
    }

    public boolean isCancelled() {
        boolean result = true;
        for (RunnableFuture future : tasks) {
            result = future.isCancelled() && result;
        }
        return result;
    }

    public boolean isDone() {
        boolean result = true;
        for (RunnableFuture future : tasks) {
            result = future.isCancelled() && result;
        }
        return result;
    }

    public Boolean get() throws InterruptedException, ExecutionException {
        for (RunnableFuture future : tasks) {
            future.get();
        }
        return Boolean.TRUE;
    }

    public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        long t = System.currentTimeMillis() + unit.toMillis(timeout);
        for (RunnableFuture future : tasks) {
            long t1 = System.currentTimeMillis() - t;
            if (t1 <= 0) {
                return Boolean.FALSE;
            }
            try {
                future.get(t1, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
