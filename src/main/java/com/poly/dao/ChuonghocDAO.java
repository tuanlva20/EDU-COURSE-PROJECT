package com.poly.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Chuonghoc;

public interface ChuonghocDAO extends JpaRepository<Chuonghoc,Integer> {
    
    @Query("select p from Chuonghoc p where p.product.id = ?1")
    List<Chuonghoc> getAllbyProduct(int id);
}
