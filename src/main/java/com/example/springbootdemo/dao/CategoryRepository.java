package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "select * from category where name = :type",nativeQuery = true)
    public Category getCategoryByType(String type);

}
