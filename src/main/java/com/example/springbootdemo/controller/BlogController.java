package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.Blog;
import com.example.springbootdemo.bean.Record;
import com.example.springbootdemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 博客的查询
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    //获取所有的文章
    @RequestMapping("/blogs")
    public List<Blog> getAllBlog()
    {
        return blogService.getAllBlog();
    }
}
