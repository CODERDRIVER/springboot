package com.example.springbootdemo.bean;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Data;

@Data
public class Record {

    private String msg;
    private int code;
    private  String token;

    public Record()
    {

    }
    public Record(String msg,int code)
    {
        this.msg = msg;
        this.code = code;
    }
}
