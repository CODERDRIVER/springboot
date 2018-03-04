package com.example.springbootdemo.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletionException;

/**
 * 博主,普通用户
 */
@Entity
@Table(name = "blogger")
@Data
public class Blogger implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String imgUrl;

    //博客
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "blogger_id")
    private List<Blog> blogs;

    //评论
    @OneToMany(mappedBy = "blogger")
    private List<Comment>  comments;

    public Blogger()
    {

    }
    public Blogger(String username,String password)
    {
        this.username = username;
        this.password = password;
    }

}
