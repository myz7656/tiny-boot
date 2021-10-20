package com.sp.tiny.boot.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sp.tiny.boot.AbstractTask;
import com.sp.tiny.boot.BootManager;
import com.sp.tiny.boot.TaskQueue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BootManager bootManager = new BootManager();
        bootManager.registerOnMainThread(new TaskMain1());
        bootManager.registerOnMainThread(new TaskMain2());
        bootManager.registerOnMainThread(new TaskMain3());
        bootManager.registerOnMainThread(new TaskMain4());

        AbstractTask taskAsync3 = new TaskAsync3();
        bootManager.registerOnAsyncThread(new TaskAsync1());
        bootManager.registerOnAsyncThread(new TaskAsync2());
        bootManager.registerOnAsyncThread(taskAsync3);
        bootManager.registerOnAsyncThread(new TaskAsync4());

        AbstractTask taskCustom1 = new TaskCustom1();
        taskCustom1.dependOn(taskAsync3);
        TaskQueue custom = new TaskQueue();
        custom.register(taskCustom1);
        custom.register(new TaskCustom2());
        custom.register(new TaskCustom3());
        custom.register(new TaskCustom4());

        bootManager.executeCustom(custom);
        bootManager.executeAsync();
        bootManager.executeMain();
    }
}