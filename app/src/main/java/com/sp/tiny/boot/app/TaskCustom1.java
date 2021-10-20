package com.sp.tiny.boot.app;

import android.util.Log;

import com.sp.tiny.boot.AbstractTask;

import java.util.Random;

public class TaskCustom1 extends AbstractTask {
    @Override
    public String getName() {
        return "TaskCustom1";
    }

    @Override
    public void execute() {
        super.execute();

        Log.i("TaskCustom1", "execute() in " + Thread.currentThread().getId());
        int random = new Random(System.currentTimeMillis()).nextInt(3000);
        try {
            Thread.sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
