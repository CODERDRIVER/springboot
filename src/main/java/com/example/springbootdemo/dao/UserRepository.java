package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.User;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

//@NoRepositoryBean
public interface UserRepository extends JpaRepository<User,Long> {


}
