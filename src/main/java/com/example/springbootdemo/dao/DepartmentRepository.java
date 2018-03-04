package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
