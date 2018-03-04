package com.example.springbootdemo.controller;

import com.example.springbootdemo.domain.AuthorSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorSetting authorSetting;

    @RequestMapping(value = "/authorIndex")
    public String index(Model model)
    {
//        model.addAttribute("title","author setting");
//        model.addAttribute("name",authorSetting.getUsername());
//        model.addAttribute("age",authorSetting.getAge());
        return "author name is "+authorSetting.getUsername()+" and age is "+authorSetting.getAge();
//        return "author";
    }
}
