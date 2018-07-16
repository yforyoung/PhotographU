package com.example.y.photographu.beans;

import java.io.Serializable;

public class School implements Serializable {
    private String name;
    private int province;
    private String city;

    public School() {
    }

    public School(String name, int province, String city) {
        this.name = name;
        this.province = province;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
