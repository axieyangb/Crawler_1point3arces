package com.jerryxie.forum.worker.task.wanqu;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.service.BayareaPostClientService;
import com.jerryxie.forum.worker.service.PostService;

@Component
@Scope("prototype")
public class FetchNewWanquItemTask implements Runnable {
    private Logger logger = Logger.getLogger(FetchNewWanquItemTask.class);

    @Autowired
    private PostService postService;

    @Autowired
    private BayareaPostClientService postClientService;

    @Autowired
    ApplicationContext context;
    @Autowired
    ThreadPoolTaskExecutor executor;

    @Override
    public void run() {
        int pageNum = postClientService.getPageNum();
        List<Future<List<String>>> results = new ArrayList<>();
        for (int i = 1; i <= pageNum; i++) {
            results.add(executor.submit(context.getBean(FetchWanquTidItemPerPageTask.class, i)));
        }
        Set<Integer> existedTids = postService.getAllPostTid();
        List<Integer> tids = new ArrayList<>();
        results.forEach(future -> {
            try {
                future.get().forEach(tidStr -> {
                    int tid = Integer.parseInt(tidStr);
                    if (existedTids.add(tid)) {
                        tids.add(tid);
                    }
                });
            } catch (InterruptedException e) {
                logger.error(e);
            } catch (ExecutionException e) {
                logger.error(e);
            }
        });
        logger.info(String.format("tids for wanqu have been collected: %d", tids.size()));
        CountDownLatch latch = new CountDownLatch(tids.size());
        tids.forEach(tid ->{
            executor.submit(context.getBean(FetchWanquItemDetailTask.class, tid, latch));
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
           logger.error(e);
        }
        
        logger.info("Scheduled Job for wanqu updates is finished");
    }

}
