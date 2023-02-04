package com.poly.dao;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductDAO extends JpaRepository<Product, Integer>{
    
    @Query("select p from Product p where p.category.id=?1")
    List<Product> findByCategoryId(Optional<Integer> id);

    // @Query("select p.id from Product p")
    // List<Integer> findByid();

}
