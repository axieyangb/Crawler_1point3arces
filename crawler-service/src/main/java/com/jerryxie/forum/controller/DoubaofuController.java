package com.jerryxie.forum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jerryxie.forum.doubaofu.ItemListPageService;
import com.jerryxie.forum.doubaofu.PackageInfoService;
import com.jerryxie.forum.doubaofu.models.SalaryPackage;

@RestController
@RequestMapping("/doubaofu")
public class DoubaofuController {
    @Autowired
    PackageInfoService packageFetcher;
    @Autowired
    ItemListPageService itemListFetcher;

    @RequestMapping(value = "detail/{tid}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public SalaryPackage index(@PathVariable("tid") int tid) {
        Document doc = packageFetcher.getPackagePage(tid);
        return packageFetcher.generatePackageData(doc, tid);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<String> doubaofu(@RequestParam(value = "pagenum", required = false) Optional<Integer> pageNum) {
        List<String> items = new ArrayList<>();
        if (pageNum.isPresent()) {
            Document doc = itemListFetcher.getItemListPage(pageNum.get());
            items = itemListFetcher.getTidList(doc);
            return items;
        } else {
            Document firstDoc = itemListFetcher.getItemListPage(1);
            items = itemListFetcher.getTidList(firstDoc);
            int totalPageNum = itemListFetcher.getTotalPageNum(firstDoc);
            for (int i = 2; i <= totalPageNum; i++) {
                Document doc = itemListFetcher.getItemListPage(i);
                items.addAll(itemListFetcher.getTidList(doc));
                System.out.println("Checking " + i + "page, current list size : " + items.size());
            }
            return items;
        }
    }

    @RequestMapping(value = "pagenum", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public int getPageNum() {
        Document doc = itemListFetcher.getItemListPage(1);
        return itemListFetcher.getTotalPageNum(doc);
    }

}
