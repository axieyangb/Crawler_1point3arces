package com.jerryxie.forum.worker.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerryxie.forum.worker.domain.Post;
import com.jerryxie.forum.worker.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post insertPost(Post post) {
        return this.postRepository.insert(post);
    }
    
    public Set<Integer> getAllPostTid(){
        Set<Integer> tidSet = new HashSet<>();
        this.postRepository.findAll().forEach(post ->{
            tidSet.add(post.getTid());
        });
        return tidSet;
    }

}
