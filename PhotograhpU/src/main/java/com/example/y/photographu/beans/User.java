package com.example.y.photographu.beans;

import java.io.Serializable;

public class User implements Serializable{
    private String id;
    private String nickName;
    private String sex;
    private String birthday;
    private int headImage;
    private School school;
    private String telphone;
    private String password;

    public User(String nickName, int headImage) {
        this.nickName = nickName;
        this.headImage = headImage;
    }

    public User(String id, String nickName, String sex, String birthday, int headImage, School school, String telphone, String password) {
        this.id = id;
        this.nickName = nickName;
        this.sex = sex;
        this.birthday = birthday;
        this.headImage = headImage;
        this.school = school;
        this.telphone = telphone;
        this.password = password;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getHeadImage() {
        return headImage;
    }

    public void setHeadImage(int headImage) {
        this.headImage = headImage;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
