package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.poly.bean.Quiz;
import com.poly.dao.QuizDAO;
import com.poly.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Override
    public List<Quiz> getAllByBaihocId(int baihocid) {
        return quizDAO.getAllByBaihocId(baihocid);
    }

    @Override
    public Quiz getQuizbyId(int id) {
        return quizDAO.findById(id).get();
    }

    @Override
    public Optional<Quiz> getOneById(Integer id) {
        // TODO Auto-generated method stub
        return quizDAO.findById(id);
    }
    @Override
    public List<Quiz> getAll() {
        // TODO Auto-generated method stub
        return quizDAO.findAll();
    }

    @Override
    public Quiz create(Quiz quiz) {
        // TODO Auto-generated method stub
        return quizDAO.save(quiz);
    }

    @Override
    public Quiz update(Quiz quiz) {
        // TODO Auto-generated method stub
        return quizDAO.save(quiz);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        quizDAO.deleteById(id);;
    }


    

    

}
