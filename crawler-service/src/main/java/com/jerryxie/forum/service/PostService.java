package com.jerryxie.forum.service;

import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerryxie.forum.comment.models.CommentDetail;
import com.jerryxie.forum.comment.models.Post;

@Service
public class PostService {
    @Autowired
    CommentService commentService;
    @Autowired
    CommonService commonService;

    private Logger logger = Logger.getLogger(PostService.class);
    private final String baseUrl = "https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=%d";

    public Document getDocumentByTid(int tid) {
        String url = String.format(baseUrl, tid);
        try {
            return commonService.getConnection(url).get();
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return null;
    }

    public Post getPostDetail(Document doc, int tid) {
        Post post = new Post();
        List<CommentDetail> comments = commentService.getAllComments(doc);
        post.setComments(comments);
        post.setTid(tid);
        if (comments.size() > 0) {
            post.setPostTime(comments.get(0).getPublishTime());
            post.setAuthorName(comments.get(0).getUsername());
            post.setAuthorId(Integer.parseInt(comments.get(0).getUserId()));
            post.setContent(comments.get(0).getReplyContent());
            post.setForumName("wanqu");

            comments.remove(0);
        }
        return post;
    }
}
