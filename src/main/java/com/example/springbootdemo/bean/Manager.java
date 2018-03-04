package com.example.springbootdemo.bean;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 博客网站管理员
 */
@Entity
@Table(name = "manager")
@Data
public class Manager implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;
}
