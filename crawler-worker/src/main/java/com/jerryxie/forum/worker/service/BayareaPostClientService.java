package com.jerryxie.forum.worker.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jerryxie.forum.worker.domain.Post;

@FeignClient("crawler-service:8083/wanqu")
public interface BayareaPostClientService {
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    List<String> getTidByPageNum(@RequestParam(value = "pagenum") int pageNum);

    @RequestMapping(value = "detail/{tid}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    Post detail(@PathVariable("tid") int tid);

    @RequestMapping(value = "pagenum", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    int getPageNum();
}
