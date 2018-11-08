package com.jerryxie.forum.worker.taskitem;

import java.util.concurrent.CountDownLatch;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.domain.SalaryPackage;
import com.jerryxie.forum.worker.service.SalaryPackageClientService;
import com.jerryxie.forum.worker.service.SalaryPackageService;

@Component
@Scope("prototype")
public class FetchSalaryPackageDetailTask implements Runnable {
    private Logger logger = Logger.getLogger(FetchSalaryPackageDetailTask.class);
    private int tid;
    @Autowired
    SalaryPackageClientService salaryPackageClientService;
    @Autowired
    SalaryPackageService salaryPackageService;

    private CountDownLatch latch;
    public FetchSalaryPackageDetailTask(int tid, CountDownLatch latch) {
        this.tid = tid;
        this.latch = latch;
    }

    @Override
    public void run() {
        SalaryPackage salaryPackage = salaryPackageClientService.findPackageByDetail(tid);
        SalaryPackage savedSalaryInfo = salaryPackageService.save(salaryPackage);
        logger.info(String.format("Successfully insert one salary: %d with _id: %s into database", tid,
                savedSalaryInfo.getId()));
        latch.countDown();
    }

}
