package com.sp.tiny.boot;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * author: 后知后觉(307817387/myz7656)
 * email: whuzhanyuanmin@126.com
 */

public class TaskQueue {
    private final List<AbstractTask> mTasks;

    public TaskQueue() {
        mTasks = new LinkedList<>();
    }

    public synchronized void register(@Nullable AbstractTask task) {
        if (task != null && !mTasks.contains(task)) {
            mTasks.add(task);
            task.setQueue(this);
        }
    }

    public synchronized void unregister(@Nullable AbstractTask task) {
        if (task != null && mTasks.contains(task)) {
            mTasks.remove(task);
            task.setQueue(null);
        }
    }

    public synchronized void clear() {
        for (AbstractTask task : mTasks) {
            task.setQueue(null);
        }
        mTasks.clear();
    }

    @NonNull
    synchronized List<AbstractTask> getTasks() {
        return new LinkedList<>(mTasks);
    }
}
