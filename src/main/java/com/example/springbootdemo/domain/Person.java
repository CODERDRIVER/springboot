package com.example.springbootdemo.domain;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Person {
    private String username;
    private int age;

    @JSONField(format = "yyyy-MM-dd")
    private Date born;

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
