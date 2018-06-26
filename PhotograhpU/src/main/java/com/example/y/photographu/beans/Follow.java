package com.example.y.photographu.beans;

public class Follow {
    private int id;
    private User user;
    private Photographer photographer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhorographer(Photographer phorographer) {
        this.photographer = photographer;
    }
}
