package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.Category;
import com.example.springbootdemo.dao.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //增加一个博客类型
    @Transactional
    public void addCatatory(Category category)
    {
        categoryRepository.save(category);
    }
    //查询所有的博客类型
    public List<Category> getAllCategory()
    {
        return categoryRepository.findAll();
    }
    //根据博客类型的名称查询博客类型
    public Category findCategoryByName(String name)
    {
        return categoryRepository.getCategoryByType(name);
    }
}
