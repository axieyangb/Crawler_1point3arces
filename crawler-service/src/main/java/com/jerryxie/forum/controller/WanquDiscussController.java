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

import com.jerryxie.forum.ForumConstants;
import com.jerryxie.forum.comment.models.Post;
import com.jerryxie.forum.service.ItemListPageService;
import com.jerryxie.forum.service.PostService;

@RestController
@RequestMapping("/wanqu")
public class WanquDiscussController {
    @Autowired
    ItemListPageService itemListFetcher;

    @Autowired
    PostService postService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<String> wanqu(@RequestParam(value = "pagenum", required = false) Optional<Integer> pageNum) {
        List<String> items = new ArrayList<>();
        if (pageNum.isPresent()) {
            Document doc = itemListFetcher.getItemListPage(ForumConstants.FID_WAN_QU, pageNum.get());
            items = itemListFetcher.getTidList(doc);
            return items;
        } else {
            Document firstDoc = itemListFetcher.getItemListPage(ForumConstants.FID_WAN_QU, 1);
            items = itemListFetcher.getTidList(firstDoc);
            int totalPageNum = itemListFetcher.getTotalPageNum(firstDoc);
            for (int i = 2; i <= totalPageNum; i++) {
                Document doc = itemListFetcher.getItemListPage(ForumConstants.FID_WAN_QU, i);
                items.addAll(itemListFetcher.getTidList(doc));
                System.out.println("Checking " + i + "page, current list size : " + items.size());
            }
            return items;
        }
    }

    @RequestMapping(value = "detail/{tid}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Post detail(@PathVariable("tid") int tid) {
        Document doc = postService.getDocumentByTid(tid);
        return postService.getPostDetail(doc, tid);
    }

    @RequestMapping(value = "pagenum", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public int getPageNum() {
        Document doc = itemListFetcher.getItemListPage(ForumConstants.FID_WAN_QU, 1);
        return itemListFetcher.getTotalPageNum(doc);
    }

}
