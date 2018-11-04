package com.jerryxie.forum.worker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jerryxie.forum.worker.client.SalaryPackageService;
import com.jerryxie.forum.worker.domain.SalaryPackage;

@RestController
public class HomeController {
    @Autowired
    SalaryPackageService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public SalaryPackage index(int tid) {
        return service.findPackageByDetail(tid);
    }
}
