package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "delete from comment where id = :id",nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteById(Long id);
}
