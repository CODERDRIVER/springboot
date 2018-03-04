package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.Blogger;
import com.example.springbootdemo.bean.Comment;
import com.example.springbootdemo.bean.Manager;
import com.example.springbootdemo.dao.BloggerRepository;
import com.example.springbootdemo.dao.CommentRepository;
import com.example.springbootdemo.dao.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员
 *      删除博主
 *      增加博主
 *      增加评论
 *      删除评论
 *      修改博主信息
 *      查询博主信息
 *
 */
@Service
public class ManagerService {

    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ManagerRepository managerRepository;

    //查询管理员用户名和密码
    public List<Manager> findAllManager()
    {
        return managerRepository.findAll();
    }

    //增加一个博主
    public void  addBlogger(Blogger blogger)
    {
        bloggerRepository.save(blogger);
    }

    //删除一个博主
    public void  deleteBlogger(Long id)
    {
        //根据id删除博主
        bloggerRepository.delete(id);
    }

    //更新博主 ,修改博主信息
    public void updateBlogger(Blogger blogger)
    {
        bloggerRepository.save(blogger);
    }

    //查询博主信息 根据id进行查询
    public Blogger findBloggerById(Long id)
    {
        return bloggerRepository.findOne(id);
    }

    //查询所有的博主信息
    public List<Blogger> findAllBlogger()
    {
        return bloggerRepository.findAll();
    }

    //增加评论
    public void addComment(Comment comment)
    {
        commentRepository.save(comment);
    }
    //删除评论
    public void deleteComment(Long id)
    {
        commentRepository.delete(id);
    }
}
