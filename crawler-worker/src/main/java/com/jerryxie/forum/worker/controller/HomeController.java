package com.jerryxie.forum.worker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jerryxie.forum.worker.domain.SalaryPackage;
import com.jerryxie.forum.worker.service.SalaryPackageClientService;
import com.jerryxie.forum.worker.service.SalaryPackageService;

@RestController
public class HomeController {
    @Autowired
    SalaryPackageClientService service;
    @Autowired
    SalaryPackageService saveService;

    @RequestMapping(value = "detail", method = RequestMethod.GET, produces = "application/json")
    public SalaryPackage index(int tid) {
        return service.findPackageByDetail(tid);
    }

    @RequestMapping(value = "tid", method = RequestMethod.GET, produces = "application/json")
    public List<String> getTidByPageNum(int pagenum) {
        return service.doubaofu(pagenum);
    }

    @RequestMapping(value = "add", method = RequestMethod.GET, produces = "application/json")
    public SalaryPackage addSalary() {
        return saveService.insertSalaryPackage(new SalaryPackage());
    }
}
