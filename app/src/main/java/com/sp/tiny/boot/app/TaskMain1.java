package com.sp.tiny.boot.app;

import android.util.Log;

import com.sp.tiny.boot.AbstractTask;

import java.util.Random;

public class TaskMain1 extends AbstractTask {
    @Override
    public String getName() {
        return "TaskMain1";
    }

    @Override
    public void execute() {
        super.execute();

        Log.i("TaskMain1", "execute() in " + Thread.currentThread().getId());
        int random = new Random(System.currentTimeMillis()).nextInt(500);
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
