package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Category;
import com.poly.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    import org.springframework.web.bind.annotation.DeleteMapping;

    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
    import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("*")
@RequestMapping("/rest/categories")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Category> getOne(@PathVariable("id") Integer id){
        return categoryService.getOne(id);
    }

    @PostMapping()
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @PutMapping()
    public Category update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
    }
}
