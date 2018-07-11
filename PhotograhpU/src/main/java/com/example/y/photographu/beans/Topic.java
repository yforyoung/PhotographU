package com.example.y.photographu.beans;

import java.util.List;

public class Topic {
    private User user;
    private String  content;
    private int contentImage;
    private int thumbUp;
    private List<Comment> commentList;
    private Style Style;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentImage() {
        return contentImage;
    }

    public void setContentImage(int contentImage) {
        this.contentImage = contentImage;
    }

    public int getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(int thumbUp) {
        this.thumbUp = thumbUp;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Style getStyle() {
        return Style;
    }

    public void setStyle(Style Style) {
        this.Style = Style;
    }

    public Topic(User user, String content, int contentImage, int thumbUp, List<Comment> commentList, Style Style) {
        this.user = user;
        this.content = content;
        this.contentImage = contentImage;
        this.thumbUp = thumbUp;
        this.commentList = commentList;
        this.Style = Style;
    }

    public Topic() {
    }
}
