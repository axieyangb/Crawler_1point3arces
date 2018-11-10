package com.jerryxie.forum.worker.task.wanqu;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jerryxie.forum.worker.domain.Post;
import com.jerryxie.forum.worker.service.BayareaPostClientService;
import com.jerryxie.forum.worker.service.PostService;

@Component
@Scope("prototype")
public class FetchWanquItemDetailTask implements Runnable {
    private int tid;
    @Autowired
    BayareaPostClientService postClientService;
    @Autowired
    PostService postService;

    private CountDownLatch latch;

    public FetchWanquItemDetailTask(int tid, CountDownLatch latch) {
        this.tid = tid;
        this.latch = latch;
    }

    @Override
    public void run() {
        Post post = this.postClientService.detail(tid);
        this.postService.insertPost(post);
        this.latch.countDown();

    }

}
