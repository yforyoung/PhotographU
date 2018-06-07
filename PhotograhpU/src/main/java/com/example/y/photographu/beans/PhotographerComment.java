package com.example.y.photographu.beans;

public class PhotographerComment {
    private User judgeUser;
    private Photographer photographer;
    private String content;

    public User getJudgeUser() {
        return judgeUser;
    }

    public void setJudgeUser(User judgeUser) {
        this.judgeUser = judgeUser;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PhotographerComment() {
    }

    public PhotographerComment(User judgeUser, Photographer photographer, String content) {
        this.judgeUser = judgeUser;
        this.photographer = photographer;
        this.content = content;
    }
}
