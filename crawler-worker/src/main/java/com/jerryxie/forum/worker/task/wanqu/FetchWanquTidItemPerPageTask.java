package com.jerryxie.forum.worker.task.wanqu;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.service.BayareaPostClientService;

@Component
@Scope("prototype")
public class FetchWanquTidItemPerPageTask implements Callable<List<String>> {
    @Autowired
    private BayareaPostClientService postClientService;

    private int pageNum;

    public FetchWanquTidItemPerPageTask(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public List<String> call() throws Exception {
        return postClientService.getTidByPageNum(pageNum);
    }

}
