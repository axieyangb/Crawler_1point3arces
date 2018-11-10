package com.jerryxie.forum.worker;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.task.salary.FetchNewSalaryItemTask;
import com.jerryxie.forum.worker.task.wanqu.FetchNewWanquItemTask;

@Component
public class WorkerInitializer {

    @Autowired
    ScheduledThreadPoolExecutor executor;
    @Autowired
    private ApplicationContext appContext;

    private final int firstDelayTimeLonger = 60;
    private final int firstDelayTimeshorter = 30;
    private final int intervalTime = 60 * 10;

    public void initWorkerToFetchSalaryTidList() {
        FetchNewSalaryItemTask salaryTask = appContext.getBean(FetchNewSalaryItemTask.class);
        FetchNewWanquItemTask wanquTask = appContext.getBean(FetchNewWanquItemTask.class);
        executor.scheduleWithFixedDelay(salaryTask, firstDelayTimeLonger, intervalTime, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(wanquTask, firstDelayTimeshorter, intervalTime, TimeUnit.SECONDS);

    }
}
