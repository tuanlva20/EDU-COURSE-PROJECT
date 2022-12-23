package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Thaoluan;

public interface ThaoluanDAO extends JpaRepository<Thaoluan,Integer> {
    
    @Query("select p from Thaoluan p where p.baihoc.baihocid = ?1")
    List<Thaoluan> getByBaihoc(int baihocid);
}
