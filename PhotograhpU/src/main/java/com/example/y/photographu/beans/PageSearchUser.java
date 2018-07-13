package com.example.y.photographu.beans;

public class PageSearchUser {
    private String nickname;
    private String headImage;

    public PageSearchUser() {
    }

    public PageSearchUser(String nickname, String headImage) {
        this.nickname = nickname;
        this.headImage = headImage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}
