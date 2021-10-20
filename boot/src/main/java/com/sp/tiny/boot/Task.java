package com.sp.tiny.boot;

import android.support.annotation.NonNull;

/**
 * author: 后知后觉(307817387/myz7656)
 * email: whuzhanyuanmin@126.com
 */

public interface Task {
    /**
     * Task 的状态
     */
    int INIT = 0;
    int RUNNING = 1;
    int FINISHED = 2;

    /**
     * @return 任务名，用于统计和日志记录
     */
    @NonNull
    String getName();

    /**
     * 执行启动任务
     */
    void execute();

    /**
     * 用于配置不同启动线程之间的任务依赖关系，不能避免出现循环依赖
     * @param task 其他启动线程的任务
     */
    void dependOn(@NonNull Task task);
}
