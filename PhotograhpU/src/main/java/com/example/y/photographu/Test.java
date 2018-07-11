package com.example.y.photographu;

import com.example.y.photographu.beans.User;


public class Test {
    private static final Test test=new Test();
    public User user;
    public String cookie="";


    private Test(){}

    public static Test getInstance() {
            return test;
    }

}
