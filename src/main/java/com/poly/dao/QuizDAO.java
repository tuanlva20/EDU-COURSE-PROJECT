package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Lambai;
import com.poly.bean.Quiz;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {
    
    @Query("select p from Quiz p where p.baihoc.baihocid = ?1")
    List<Quiz> getAllByBaihocId(int baihocid);


    @Query(value="SELECT distinct quiz.* FROM lambai,quiz, products,baihoc,chuonghoc where lambai.quizid = quiz.idquiz and quiz.idbaihoc=baihoc.baihocid and baihoc.chuonghocid = chuonghoc.id and chuonghoc.productid = products.id and productid = ?1 and lambai.username = ?2 and quiz.idbaihoc= ?3",nativeQuery=true)
    List<Quiz> getAllbyProductandUsername(int productid, String username,int idbaihoc);
}
