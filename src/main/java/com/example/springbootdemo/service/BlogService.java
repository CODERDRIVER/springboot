package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.Blog;
import com.example.springbootdemo.dao.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    //查询所有的博客
    public List<Blog> getAllBlog()
    {
        return blogRepository.findAll();
    }
    //更改博客
    @Transactional
    public boolean updateBlog(Blog blog)
    {
        blogRepository.save(blog);
        return true;
    }

    //根据博主的id查询博客
    public List<Blog> getAllBlogByBloggerId(Long id)
    {
        return blogRepository.getAllBlogByBloggerId(id);
    }

}
