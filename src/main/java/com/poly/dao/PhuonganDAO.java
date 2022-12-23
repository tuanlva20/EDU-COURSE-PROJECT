package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Phuongan;

public interface PhuonganDAO extends JpaRepository<Phuongan,Integer> {
    @Query("select p from Phuongan p where p.quiz.idquiz = ?1")
    List<Phuongan> getAllByIdQuiz(int idquiz);
}
