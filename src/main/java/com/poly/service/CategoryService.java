package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Category;

public interface CategoryService {

    List<Category> getAll();
    Optional<Category> getOne(Integer id);

    Category create(Category category);

    Category update(Category category);

    void delete(Integer id);

}
