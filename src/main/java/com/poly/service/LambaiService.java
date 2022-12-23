package com.poly.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.bean.Lambai;

public interface LambaiService {
    
    Lambai create(JsonNode lambai);
    Lambai getAllbyUserAndQuiz(String username,int quizid);

    List<Lambai> getAllbyUser(String username);

    
}
