package com.example.y.photographu.beans;

public class Comment {
    private User judgeUser;
    private Topic topic;
    private String content;

    public Comment() {
    }

    public User getJudgeUser() {
        return judgeUser;
    }

    public void setJudgeUser(User judgeUser) {
        this.judgeUser = judgeUser;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment(User judgeUser, Topic topic, String content) {
        this.judgeUser = judgeUser;
        this.topic = topic;
        this.content = content;
    }
}
