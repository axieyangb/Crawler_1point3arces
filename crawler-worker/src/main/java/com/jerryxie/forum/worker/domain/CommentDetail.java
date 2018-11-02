package com.jerryxie.forum.worker.domain;

public class CommentDetail {
    private String quoteTitle;
    private String quoteContent;
    private String replyContent;

    public String getQuoteTitle() {
        return quoteTitle;
    }

    public void setQuoteTitle(String quoteTitle) {
        this.quoteTitle = quoteTitle;
    }

    public String getQuoteContent() {
        return quoteContent;
    }

    public void setQuoteContent(String quoteContent) {
        this.quoteContent = quoteContent;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public boolean isLeader() {
        return isLeader;
    }

    @Override
    public String toString() {
        return "CommentDetail [quoteTitle=" + quoteTitle + ", quoteContent=" + quoteContent + ", replyContent="
                + replyContent + ", isLeader=" + isLeader + ", username=" + username + ", userId=" + userId
                + ", publishTime=" + publishTime + "]";
    }

    public void setLeader(boolean isLeader) {
        this.isLeader = isLeader;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    private boolean isLeader;
    private String username;
    private String userId;
    private long publishTime;

}
