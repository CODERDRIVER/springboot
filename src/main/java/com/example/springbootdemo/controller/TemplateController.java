package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class TemplateController {

    @RequestMapping("/hello")
    public String getHello(){
        return "hello";
    }
}
