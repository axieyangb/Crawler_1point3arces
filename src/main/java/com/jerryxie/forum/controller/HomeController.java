package com.jerryxie.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerryxie.forum.doubaofu.DoubaofuConstants;
import com.jerryxie.forum.doubaofu.PackageInfoFetcher;

@RestController
public class HomeController {
    @Autowired
    PackageInfoFetcher fetcher;

    @RequestMapping("/")
    public String Index() {
        System.out.println(fetcher.getBase(453779));
        return "Hello World";
    }
}
