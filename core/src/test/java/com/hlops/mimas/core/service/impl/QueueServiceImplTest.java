package com.hlops.mimas.core.service.impl;

import com.google.inject.Inject;
import com.hlops.mimas.core.data.EntityKey;
import com.hlops.mimas.core.data.TaskKey;
import com.hlops.mimas.core.inject.CoreGuiceModule;
import com.hlops.mimas.core.service.QueueService;
import com.hlops.mimas.core.sync.CallableTask;
import com.hlops.mimas.test.GuiceJUnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 17.08.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules({CoreGuiceModule.class})
public class QueueServiceImplTest extends Assert {

    @Inject
    private QueueService queueService;

    private class TestTask implements CallableTask<String> {

        private TaskKey key;
        private String name;
        private int threadDuration;

        TestTask(String name, int threadDuration, int keyTimeout) {
            this.name = name;
            this.threadDuration = threadDuration;
            key = new TaskKey(this.getClass(), new StringKey(name));
            key.setTimeout(keyTimeout);
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(threadDuration);
            return name + " done " + System.currentTimeMillis();
        }

        @Override
        public TaskKey getKey() {
            return key;
        }

        @Override
        public boolean isBlocking() {
            return false;
        }
    }

    private class StringKey implements EntityKey {
        private String name;

        public StringKey(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StringKey)) return false;

            StringKey stringKey = (StringKey) o;

            if (name != null ? !name.equals(stringKey.name) : stringKey.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    @Test
    public void testGetFuture1() throws Exception {
        final ConcurrentHashMap<Integer, String> result = new ConcurrentHashMap<Integer, String>();
        final List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Future<String> task = queueService.getFuture(new TestTask("task1", 0, 500));
                    try {
                        task.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                    }
                    result.put(task.hashCode(), "");
                }
            };
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(result.size(), 1);
    }

    @Test
    public void testGetFuture2() throws Exception {
        final ConcurrentHashMap<Integer, String> result = new ConcurrentHashMap<Integer, String>();
        for (int i = 0; i < 100; i++) {
            Future<String> task = queueService.getFuture(new TestTask("task1", 50, 100));
            try {
                task.get();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
            result.put(task.hashCode(), "");
        }

        assertEquals(result.size(), 1);
    }

    @Test
    public void testGetFuture3() throws Exception {
        final ConcurrentHashMap<Integer, String> result = new ConcurrentHashMap<Integer, String>();
        final List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Future<String> task = queueService.getFuture(new TestTask("task" + finalI, 0, 0));
                    try {
                        task.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        fail();
                    }
                    result.put(task.hashCode(), "");
                }
            };
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(result.size(), 100);
    }

}
