package com.poly.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Account;
import com.poly.bean.Lambai;
import com.poly.bean.Quiz;
import com.poly.dao.LambaiDAO;
import com.poly.service.LambaiService;

@Service
public class LambaiServiceImpl implements LambaiService {

    @Autowired
    LambaiDAO lambaiDAO;
    @Override
    public Lambai create(JsonNode lambai) {
        ObjectMapper mapper=new ObjectMapper();
        System.out.println(lambai);
        Lambai lambaiConvert = mapper.convertValue(lambai, Lambai.class);
        lambaiDAO.save(lambaiConvert);
        return lambaiConvert;
    }
   
    @Override
    public Lambai getAllbyUserAndQuiz(String username,int quizid) {
        // TODO Auto-generated method stub
        return lambaiDAO.getAllbyUserAndQuiz(username,quizid);
    }

    
    @Override
    public List<Lambai> getAllbyUser(String username) {
        return lambaiDAO.getAllbyUser(username);
    }

 

    
}
