package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Baihoc;



public interface BaihocDAO extends JpaRepository<Baihoc,Integer> {
    
    @Query(value="SELECT * FROM baihoc where chuonghocid = ?1",nativeQuery = true)
    List<Baihoc> getListByIdChuonghoc(int id);
   
}
