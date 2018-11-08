package com.jerryxie.forum.worker.taskitem;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.service.SalaryPackageClientService;

@Component
@Scope("prototype")
public class FetchSalaryItemPerPageTask implements Callable<List<String>> {
    @Autowired
    SalaryPackageClientService salaryPackageClientService;

    private int pageNum;

    public FetchSalaryItemPerPageTask(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> tids = salaryPackageClientService.doubaofu(pageNum);
        return tids;
    }

}
