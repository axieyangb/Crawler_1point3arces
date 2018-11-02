package com.jerryxie.forum.worker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerryxie.forum.worker.client.SalaryPackageService;

@RestController
public class HomeController {
    @Autowired
    SalaryPackageService service;

    @RequestMapping("")
    public String index(int tid) {
        return service.findPackageByDetail(tid).toString();
    }
}
