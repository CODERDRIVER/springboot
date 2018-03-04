package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query(value = "select * from blog where blogger_id=:id",nativeQuery = true)
    public List<Blog> getAllBlogByBloggerId(@Param("id") Long id);

}
