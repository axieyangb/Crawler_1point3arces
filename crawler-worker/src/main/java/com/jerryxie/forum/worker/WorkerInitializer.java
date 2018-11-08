package com.jerryxie.forum.worker;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.taskitem.FetchNewSalaryItemTask;

@Component
public class WorkerInitializer {

    @Autowired
    ScheduledThreadPoolExecutor executor;
    @Autowired
    private ApplicationContext appContext;

    private final int firstDelayTime = 60;
    private final int intervalTime = 60 * 10;

    public void initWorkerToFetchSalaryTidList() {
        FetchNewSalaryItemTask task = appContext.getBean(FetchNewSalaryItemTask.class);
        executor.scheduleWithFixedDelay(task, firstDelayTime, intervalTime, TimeUnit.SECONDS);

    }
}
