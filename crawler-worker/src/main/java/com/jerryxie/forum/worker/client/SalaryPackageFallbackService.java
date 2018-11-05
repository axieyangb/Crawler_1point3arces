package com.jerryxie.forum.worker.client;

import org.jboss.logging.Logger;

import com.google.gson.GsonBuilder;
import com.jerryxie.forum.worker.domain.SalaryPackage;

public class SalaryPackageFallbackService implements SalaryPackageService {
    private final Throwable cause;
    private Logger logger = Logger.getLogger(SalaryPackageFallbackService.class);

    public SalaryPackageFallbackService(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public SalaryPackage findPackageByDetail(int tid) {
        logger.error(new GsonBuilder().setPrettyPrinting().create().toJson(cause));
        logger.error(cause.getCause());
        SalaryPackage salaryPackage = new SalaryPackage();
        salaryPackage.setTid(-1);
        salaryPackage.setComment("Unknown");
        salaryPackage.setBase(0);
        return salaryPackage;
    }

}
