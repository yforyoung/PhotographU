package com.example.y.photographu.beans;

import java.util.List;

public class Topic {
    private User user;
    private String  content;
    private int contentImage;
    private int thumbUp;
    private List<Comment> commentList;
    private Type type;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Topic(User user, String content, int contentImage, int thumbUp, List<Comment> commentList, Type type) {
        this.user = user;
        this.content = content;
        this.contentImage = contentImage;
        this.thumbUp = thumbUp;
        this.commentList = commentList;
        this.type = type;
    }

    public Topic() {
    }
}
