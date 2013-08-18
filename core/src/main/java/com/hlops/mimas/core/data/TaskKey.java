package com.hlops.mimas.core.data;

import com.hlops.mimas.core.sync.AbstractTask;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tom
 * Date: 12.04.13
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public class TaskKey implements EntityKey {

    private final Class<? extends AbstractTask> taskClass;
    private final EntityKey key;
    private long timeout;

    public TaskKey(@NotNull Class<? extends AbstractTask> taskClass, @NotNull EntityKey entityKey) {
        this.taskClass = taskClass;
        this.key = entityKey;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskKey)) return false;

        TaskKey taskKey = (TaskKey) o;

        if (!key.equals(taskKey.key)) return false;
        if (!taskClass.equals(taskKey.taskClass)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskClass.hashCode();
        result = 31 * result + key.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return taskClass.getSimpleName() + ":" + key;
    }
}
