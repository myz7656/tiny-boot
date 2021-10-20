package com.sp.tiny.boot;

import android.util.Log;

/**
 * author: 后知后觉(307817387/myz7656)
 * email: whuzhanyuanmin@126.com
 */

public class Statistics {
    public static void record(String name, int time) {
        Log.i("boot", "task: " + name + ", execute " + time);
    }
}
