package com.jerryxie.forum.worker.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Post")
public class Post {
    private long postTime;
    private String authorName;
    private int authorId;
    private List<CommentDetail> comments;
    private String content;
    private String forumName;
    private int tid;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommentDetail> getComments() {
        return comments;
    }

    public void setComments(List<CommentDetail> comments) {
        this.comments = comments;
    }

    public long getPostTime() {
        return postTime;
    }

    @Override
    public String toString() {
        return "Post [postTime=" + postTime + ", authorName=" + authorName + ", authorId=" + authorId + ", comments="
                + comments + ", content=" + content + ", forumName=" + forumName + "]";
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Post() {
        this.comments = new ArrayList<>();
    }
}
