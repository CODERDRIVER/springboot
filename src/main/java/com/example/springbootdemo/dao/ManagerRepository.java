package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Blogger;
import com.example.springbootdemo.bean.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long>{


}
