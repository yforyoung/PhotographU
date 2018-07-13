package com.example.y.photographu.beans;

import java.util.Date;
import java.util.List;

public class Topic {
    private int id;
    private int userId;
    private int styleId;
    private String releaseTime;
    private PageSearchUser user;
    private String  content;
    private List<Image> images;
    private int admireCount;
    private int commentCount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public PageSearchUser getUser() {
        return user;
    }

    public void setUser(PageSearchUser user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getAdmireCount() {
        return admireCount;
    }

    public void setAdmireCount(int admireCount) {
        this.admireCount = admireCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Topic(int id, int userId, int styleId, String releaseTime, PageSearchUser user, String content, List<Image> images, int admireCount, int commentCount) {
        this.id = id;
        this.userId = userId;
        this.styleId = styleId;
        this.releaseTime = releaseTime;
        this.user = user;
        this.content = content;
        this.images = images;
        this.admireCount = admireCount;
        this.commentCount = commentCount;
    }

    public Topic() {
    }
}
