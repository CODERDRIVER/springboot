package com.example.springbootdemo.controller;

import com.example.springbootdemo.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String showHello()
    {
        return "hello  world";
    }

    /**
     * Spring  boot默认使用的json解析框架是jackson
     * @return
     */
    @RequestMapping("/show")
    public Person showPerson(){
        Person person = new Person();
        person.setAge(10);
        person.setUsername("刘旭阳");
        person.setBorn(new Date());
        return person;
    }
}
