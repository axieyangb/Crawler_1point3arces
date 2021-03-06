package com.jerryxie.forum.worker.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerryxie.forum.worker.client.salary.SalaryPackageClientFallbackFactory;
import com.jerryxie.forum.worker.domain.SalaryPackage;

@FeignClient(value = "crawler-service:8083/doubaofu", fallbackFactory = SalaryPackageClientFallbackFactory.class)
public interface SalaryPackageClientService {
    @RequestMapping(value = "detail/{tid}", method = RequestMethod.GET)
    SalaryPackage findPackageByDetail(@PathVariable(name = "tid", required = true) int tid);

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    List<String> doubaofu(@RequestParam(value = "pagenum") int pageNum);

    @RequestMapping(value = "pagenum", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    int getPageNum();
}
