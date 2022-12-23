package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Product;

public interface ProductService {

    List<Product> getAll();
    Product getOneById(int id);
    List<Product> getByCategoryId(Optional<Integer> id);
    Product create(Product product);
    Product update(Product product);
    void delete(Integer id);
    
}
