package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger,Long> {

    @Query(value = "select * from blogger where blogger.username=:username",nativeQuery = true)
    public Blogger findBloggerByUsername(@Param("username") String username);

}
