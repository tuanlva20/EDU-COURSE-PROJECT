package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;

import com.poly.bean.Quiz;

public interface QuizService {
    
    List<Quiz> getAllByBaihocId(int baihocid);
    Quiz getQuizbyId(int id);
    Optional<Quiz> getOneById(Integer id);
    Quiz create(Quiz quiz);
    Quiz update(Quiz quiz);
    void delete(Integer id);
   
    List<Quiz> getAll();
   
}
