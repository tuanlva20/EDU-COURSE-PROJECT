package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Account;
import com.poly.bean.Baihoc;
import com.poly.bean.Test;
import com.poly.dao.AccountDAO;
import com.poly.dao.BaihocDAO;
import com.poly.dao.TestDao;
import com.poly.service.AccountService;
import com.poly.service.BaihocService;
import java.util.Optional;
import com.poly.service.TestService;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    @Autowired
    AccountService accountService;
    @Autowired
    BaihocService baihocsService;

    @Override
    public Test getOneByUsernameAndBaihoc(String username, int baihocid) {
        return testDao.getOneByUsernameAndBaihoc(username, baihocid);
    }

    @Override
    public Test updatecode(Test test) {
        return testDao.save(test);
    }

    @Override
    public Test savecode(JsonNode test) {
        ObjectMapper objectMapper = new ObjectMapper();
        Test testFormat = objectMapper.convertValue(test, Test.class);
        return testDao.save(testFormat);
    }


    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        testDao.deleteById(id);;
    }

    @Override
    public List<Test> getAll() {
        // TODO Auto-generated method stub
        return testDao.findAll();
    }

    @Override
    public Optional<Test> getOneById(int id) {
        // TODO Auto-generated method stub
        return testDao.findById(id);
    
    }
}
