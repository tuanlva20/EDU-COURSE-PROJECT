package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Test;

public interface TestDao extends JpaRepository<Test,Integer>{
    
    @Query("select p from Test p where p.account.username = ?1 and p.baihoc.baihocid = ?2")
    Test getOneByUsernameAndBaihoc(String username , int idbaihoc);

    // @Query(value="update test set codes = ?1 where baihocid = ?2 and username = ?3", nativeQuery = true)
    // Test updatecode(String codes,int idbaihoc,String username );
}
