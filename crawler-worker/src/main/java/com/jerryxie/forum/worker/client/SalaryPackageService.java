package com.jerryxie.forum.worker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jerryxie.forum.worker.domain.SalaryPackage;

@FeignClient(value = "crawler-service:8083")
public interface SalaryPackageService {
    @RequestMapping(value = "/doubaofu/detail/{tid}", method = RequestMethod.GET)
    SalaryPackage findPackageByDetail(@PathVariable(name = "tid", required = true) int tid);
}
