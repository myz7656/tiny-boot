package com.sp.tiny.boot.app;

import android.util.Log;

import com.sp.tiny.boot.AbstractTask;

import java.util.Random;

public class TaskAsync3 extends AbstractTask {
    @Override
    public String getName() {
        return "TaskAsync3";
    }

    @Override
    public void execute() {
        super.execute();

        Log.i("TaskAsync3", "execute() in " + Thread.currentThread().getId());
        int random = new Random(System.currentTimeMillis()).nextInt(3000);
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
