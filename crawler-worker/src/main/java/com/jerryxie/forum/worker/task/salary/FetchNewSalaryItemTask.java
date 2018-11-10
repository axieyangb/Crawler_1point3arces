package com.jerryxie.forum.worker.task.salary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.service.SalaryPackageClientService;
import com.jerryxie.forum.worker.service.SalaryPackageService;

@Component
@Scope("prototype")
public class FetchNewSalaryItemTask implements Runnable {

    private Logger logger = Logger.getLogger(FetchNewSalaryItemTask.class);
    @Autowired
    SalaryPackageClientService salaryPackageClientService;
    @Autowired
    SalaryPackageService salaryPackageService;
    @Autowired
    ApplicationContext context;
    @Autowired
    ThreadPoolTaskExecutor executor;

    @Override
    public void run() {
        int pageNum = this.salaryPackageClientService.getPageNum();
        List<Future<List<String>>> results = new ArrayList<>();
        for (int i = 1; i <= pageNum; i++) {
            results.add(executor.submit(context.getBean(FetchSalaryItemPerPageTask.class, i)));
        }
        List<String> tids = new ArrayList<>();
        Set<Integer> existedTids = salaryPackageService.getAllTids();
        for (int i = 0; i < results.size(); i++) {
            try {
                results.get(i).get().forEach(tid -> {
                    if (existedTids.add(Integer.parseInt(tid))) {
                        tids.add(tid);
                    }
                });

            } catch (InterruptedException e) {
                logger.error(e);
            } catch (ExecutionException e) {
                logger.error(e);
            }
        }
        logger.info(String.format("tids for salary have been collected: %d", tids.size()));

        CountDownLatch doneSignal = new CountDownLatch(tids.size());
        tids.forEach(tid -> {
            this.executor
                    .submit(context.getBean(FetchSalaryPackageDetailTask.class, Integer.parseInt(tid), doneSignal));
        });
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            logger.error(e);
        }
        logger.info("Scheduled Job for salary updates is finished");
    }

}
