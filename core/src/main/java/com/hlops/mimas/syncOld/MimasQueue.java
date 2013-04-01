package com.hlops.mimas.syncOld;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: a.karnachuk
 * Date: 1/29/13
 * Time: 11:50 AM
 */
public class MimasQueue implements DoneHandler {

    private static final MimasQueue instance = new MimasQueue();

    public static MimasQueue getInstance() {
        return instance;
    }

    private final ThreadPoolExecutor threadExecutor;
    private final ConcurrentHashMap<Object, LockerFuture> lockers;

    private MimasQueue() {
        // todo
        int nThreads = 10;// MimasConfig.getInstance().getExecutorSize();
        threadExecutor = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        lockers = new ConcurrentHashMap<Object, LockerFuture>();
    }

    public <T extends RunnableFuture> T execute(@NotNull T runnable) {
        threadExecutor.submit(runnable);
        return runnable;
    }

    public LockerFuture execute(LockerFuture locker) {
        LockerFuture oldLocker = lockers.putIfAbsent(locker.getLock(), locker);
        if (oldLocker != null) {
            return oldLocker;
        } else {
            locker.setDoneHandler(this);
            return (LockerFuture) execute((RunnableFuture) locker);
        }
    }

    public void onDone(LockerFuture locker) {
        lockers.remove(locker.getLock());
    }
}
