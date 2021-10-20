package com.sp.tiny.boot.app;

import android.util.Log;

import com.sp.tiny.boot.AbstractTask;

import java.util.Random;

public class TaskMain2 extends AbstractTask {
    @Override
    public String getName() {
        return "TaskMain2";
    }

    @Override
    public void execute() {
        super.execute();

        Log.i("TaskMain2", "execute() in " + Thread.currentThread().getId());
        int random = new Random(System.currentTimeMillis()).nextInt(500);
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
