package com.example.y.photographu.beans;

import java.io.Serializable;

public class School implements Serializable {
    private String name;
    private String provance;
    private String city;

    public School() {
    }

    public School(String name, String provance, String city) {
        this.name = name;
        this.provance = provance;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvance() {
        return provance;
    }

    public void setProvance(String provance) {
        this.provance = provance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
