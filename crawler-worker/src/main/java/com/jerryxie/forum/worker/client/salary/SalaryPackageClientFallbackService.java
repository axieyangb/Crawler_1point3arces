package com.jerryxie.forum.worker.client.salary;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jerryxie.forum.worker.domain.SalaryPackage;
import com.jerryxie.forum.worker.service.SalaryPackageClientService;

public class SalaryPackageClientFallbackService implements SalaryPackageClientService {
    private final Throwable cause;
    private Logger logger = Logger.getLogger(SalaryPackageClientFallbackService.class);
    private final Gson gson;

    public SalaryPackageClientFallbackService(Throwable cause) {
        this.cause = cause;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public SalaryPackage findPackageByDetail(int tid) {
        logger.error(gson.toJson(cause));
        SalaryPackage salaryPackage = new SalaryPackage();
        salaryPackage.setTid(-1);
        salaryPackage.setComment("Unknown");
        salaryPackage.setBase(0);
        return salaryPackage;
    }

    @Override
    public List<String> doubaofu(int pageNum) {
        logger.error(gson.toJson(cause));
        return new ArrayList<>();
    }

    @Override
    public int getPageNum() {
        logger.error(gson.toJson(cause));
        return 0;
    }

}
