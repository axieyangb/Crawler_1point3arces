package com.jerryxie.forum.controller;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jerryxie.forum.doubaofu.ItemListPageFetcher;
import com.jerryxie.forum.doubaofu.PackageInfoFetcher;
import com.jerryxie.forum.doubaofu.models.SalaryPackage;

@RestController
public class HomeController {
    @Autowired
    PackageInfoFetcher packageFetcher;
    @Autowired
    ItemListPageFetcher itemListFetcher;
    @RequestMapping("/package")
    @ResponseBody
    public SalaryPackage Index(int tid) {
        Document doc = packageFetcher.getPackagePage(tid);
        return packageFetcher.generatePackageData(doc, tid);
    }
    @RequestMapping("/doubaofu")
    @ResponseBody
    public String Doubaofu(int pageNum) {
        Document doc = itemListFetcher.getItemListPage(pageNum);
        itemListFetcher.getTidList(doc);
        return "es";
    }
    
    
}
