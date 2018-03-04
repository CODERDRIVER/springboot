package com.example.springbootdemo.bean;

import com.sun.tools.javah.Gen;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.jvm.hotspot.memory.Generation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 博客的类别
 */
@Entity
@Table(name = "category")
@Data
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;    //类别的名称

    //@OneToMany里加入mappedBy属性可以避免生成一张中间表
    @OneToMany(mappedBy = "category") //mappedBy表示声明自己不是一对多的关系维护端，由对方来维护，是在一的一方进行声明的。mappedBy的值应该为一的一方的表名。
    private List<Blog> blogs;   //所有的博客

    public Category()
    {

    }
    public Category(String name){
        this.name  = name;
    }

}
