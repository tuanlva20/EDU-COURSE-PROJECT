package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Category;
import com.poly.dao.CategoryDAO;
import com.poly.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDAO categoryDAO;

    

    @Override
    public List<Category> getAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Optional<Category> getOne(Integer id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Category create(Category category) {
        // TODO Auto-generated method stub
        return categoryDAO.save(category);
    }

    @Override
    public Category update(Category category) {
        // TODO Auto-generated method stub
        return categoryDAO.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryDAO.deleteById(id);
        
    }
    
}
