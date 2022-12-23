package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.bean.Test;

public interface TestService {
    
    List<Test> getAll();
    Test getOneByUsernameAndBaihoc(String username , int baihocid);
    Test updatecode(Test test);
    Test savecode(JsonNode test);
    Optional<Test> getOneById(int id);

    void delete(Integer id);
    
}
