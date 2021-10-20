package com.sp.tiny.boot;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * author: 后知后觉(307817387/myz7656)
 * email: whuzhanyuanmin@126.com
 */

public abstract class AbstractTask implements Task {
    private TaskQueue mQueue;
    private AbstractTask mDepend;

    private int mState;
    private boolean mWaitState;

    public AbstractTask() {
        mQueue = null;
        mDepend = null;

        mState = Task.INIT;
        mWaitState = false;
    }

    @Override
    public void execute() {
        synchronized (this) {
            mState = Task.RUNNING;
        }
    }

    @Override
    public void dependOn(@NonNull Task task) {
        if (task instanceof AbstractTask) {
            mDepend = (AbstractTask) task;
        }
    }

    @Nullable
    AbstractTask getDepend() {
        return mDepend;
    }

    void setQueue(@Nullable TaskQueue queue) {
        mQueue = queue;
    }

    @Nullable
    TaskQueue getQueue() {
        return mQueue;
    }

    synchronized void waitFinished() {
        if (mState == Task.INIT || mState == Task.RUNNING) {
            mWaitState = true;
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
    }

    synchronized void markFinished() {
        if (mWaitState) {
            notify();
        }
        mState = Task.FINISHED;
    }
}
