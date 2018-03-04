package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.Blog;
import com.example.springbootdemo.bean.Blogger;
import com.example.springbootdemo.bean.Category;
import com.example.springbootdemo.bean.Comment;
import com.example.springbootdemo.dao.BlogRepository;
import com.example.springbootdemo.dao.BloggerRepository;
import com.example.springbootdemo.dao.CategoryRepository;
import com.example.springbootdemo.dao.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BloggerService {

    /**
     *  博主的功能：
     *      1.注册和登录
     *      2.发表博客(博客的类型）
     *      3.评论
     */
    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private static final String ROOT = "/Users/mac/Downloads/JavaCode/springbootdemo/images/";

    //注册博主
    @Transactional
    public void registerBlogger(Blogger blogger)
    {
        bloggerRepository.save(blogger);
    }
    public List<Blogger> findAllBlogger()
    {
        return bloggerRepository.findAll();
    }


    //根据用户名查询博主的信息
    public Blogger findBloggerByUsername(String username)
    {
        return  bloggerRepository.findBloggerByUsername(username);
    }

    //发表博客
    @Transactional
    public void submitBlog(Category category,Blog blog)
    {
        //保存博客类型
        categoryRepository.save(category);

        //保存博客
        blogRepository.save(blog);
    }

    //删除博客
    @Transactional
    public boolean deleteBlog(Long id)
    {
        bloggerRepository.delete(id);
        return true;
    }

    //更新博客
    @Transactional
    public void updateBlog(Blog blog)
    {
        blogRepository.save(blog);
    }


    //发表评论
    @Transactional
    public void submitComment(Comment comment){
        commentRepository.save(comment);
    }

    //删除评论
    @Transactional
    public void deleteComment(Long id)
    {
        Comment comment = new Comment();
        comment.setId(id);
        commentRepository.delete(comment);
    }

    //上传图片
    @Transactional
    public void uploadImg(Long id,String imgUrl){
        Blogger blogger = bloggerRepository.findOne(id);
        if(blogger==null){
            return ;
        }else{
            blogger.setImgUrl(ROOT+imgUrl);
            bloggerRepository.save(blogger);
        }
    }

}
