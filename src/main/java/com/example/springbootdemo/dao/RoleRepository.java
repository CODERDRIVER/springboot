package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Role;
import com.example.springbootdemo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface RoleRepository extends JpaRepository<Role,Long> {

}
