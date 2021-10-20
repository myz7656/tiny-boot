package com.sp.tiny.boot;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import java.util.List;

/**
 * author: 后知后觉(307817387/myz7656)
 * email: whuzhanyuanmin@126.com
 */

public class BootManager {
    private final TaskQueue mMainTaskQueue;
    private final TaskQueue mAsyncTaskQueue;
    private final Handler mMainHandler;

    public BootManager() {
        mMainTaskQueue = new TaskQueue();
        mAsyncTaskQueue = new TaskQueue();
        mMainHandler = new Handler(Looper.getMainLooper());
    }

    public void registerOnMainThread(AbstractTask task) {
        mMainTaskQueue.register(task);
    }

    public void registerOnAsyncThread(AbstractTask task) {
        mAsyncTaskQueue.register(task);
    }

    public void executeMain() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            executeInternal(mMainTaskQueue);
        } else {
            mMainHandler.post(() -> executeInternal(mMainTaskQueue));
        }
    }

    public void executeAsync() {
        new Thread(() -> executeInternal(mAsyncTaskQueue)).start();
    }

    public void executeCustom(TaskQueue queue) {
        new Thread(() -> executeInternal(queue)).start();
    }

    private void executeInternal(TaskQueue queue) {
        List<AbstractTask> tasks = queue.getTasks();
        for (AbstractTask task : tasks) {
            AbstractTask depends = task.getDepend();

            if (depends != null && queue != depends.getQueue()) {
                depends.waitFinished();
            }

            long current = SystemClock.currentThreadTimeMillis();
            task.execute();
            int spend = (int) (SystemClock.currentThreadTimeMillis() - current);
            Statistics.record(task.getName(), spend);

            task.markFinished();
        }
    }
}
