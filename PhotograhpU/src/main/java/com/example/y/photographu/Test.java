package com.example.y.photographu;

import com.example.y.photographu.beans.Photographer;
import com.example.y.photographu.beans.PhotographerComment;
import com.example.y.photographu.beans.Type;
import com.example.y.photographu.beans.User;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private static Test test;
    public User user;

    public static Test getInstence() {
        if (test == null)
            return new Test();
        else
            return test;
    }

}
