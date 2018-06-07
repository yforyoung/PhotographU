package com.example.y.photographu.beans;

import java.io.Serializable;
import java.util.List;

public class Photographer implements Serializable{
    private User user;
    private float price;
    private String service;
    private String introduce;
    private List<PhotographerComment> photographerCommentList;
    private List<Integer> photoList;

    public List<Integer> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Integer> photoList) {
        this.photoList = photoList;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<PhotographerComment> getPhotographerCommentList() {
        return photographerCommentList;
    }

    public void setPhotographerCommentList(List<PhotographerComment> photographerCommentList) {
        this.photographerCommentList = photographerCommentList;
    }

    public Photographer() {
    }

    public Photographer(User user, float price, String service,String introduce,List<PhotographerComment> photographerCommentList) {
        this.user = user;
        this.price = price;
        this.service = service;
        this.introduce=introduce;
        this.photographerCommentList = photographerCommentList;
    }
}
