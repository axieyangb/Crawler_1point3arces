package com.jerryxie.forum.worker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jerryxie.forum.worker.domain.Post;
import com.jerryxie.forum.worker.domain.SalaryPackage;
import com.jerryxie.forum.worker.service.BayareaPostClientService;
import com.jerryxie.forum.worker.service.PostService;
import com.jerryxie.forum.worker.service.SalaryPackageClientService;
import com.jerryxie.forum.worker.service.SalaryPackageService;

@RestController
public class HomeController {
    @Autowired
    SalaryPackageClientService salaryService;
    @Autowired
    SalaryPackageService saveService;
    @Autowired
    BayareaPostClientService bayareaPostService;
    @Autowired
    PostService postSaveService;

    @RequestMapping(value = "/doubaofu/detail", method = RequestMethod.GET, produces = "application/json")
    public SalaryPackage index(int tid) {
        return salaryService.findPackageByDetail(tid);
    }

    @RequestMapping(value = "/doubaofu/tid", method = RequestMethod.GET, produces = "application/json")
    public List<String> getTidByPageNum(int pagenum) {
        return salaryService.doubaofu(pagenum);
    }

    @RequestMapping(value = "/doubaofu/add", method = RequestMethod.GET, produces = "application/json")
    public SalaryPackage addSalary() {
        return saveService.insertSalaryPackage(new SalaryPackage());
    }
    
    @RequestMapping(value = "/wanqu/detail", method = RequestMethod.GET, produces = "application/json")
    public Post wanqu(int tid) {
        return bayareaPostService.detail(tid);
    }

    @RequestMapping(value = "/wanqu/tid", method = RequestMethod.GET, produces = "application/json")
    public List<String> getBayAreaPostTidByPageNum(int pagenum) {
        return bayareaPostService.getTidByPageNum(pagenum);
    }

    @RequestMapping(value = "/wanqu/add", method = RequestMethod.GET, produces = "application/json")
    public Post addPost() {
        return postSaveService.insertPost(new Post());
    }
}
