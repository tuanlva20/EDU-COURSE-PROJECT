package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Lambai;

public interface LambaiDAO extends JpaRepository<Lambai,Integer> {
    
    @Query("select p from Lambai p where p.account.username = ?1 and p.quiz.idquiz = ?2")
    Lambai getAllbyUserAndQuiz(String username, int idquiz);

    @Query("select p from Lambai p where p.account.username = ?1")
    List<Lambai> getAllbyUser(String username);

   

}
