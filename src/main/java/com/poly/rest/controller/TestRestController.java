package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.bean.Account;
import com.poly.bean.Test;
import com.poly.service.AccountService;
import com.poly.service.BaihocService;
import com.poly.service.TestService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/test")
public class TestRestController {
    
    @Autowired
    TestService testService;
    @Autowired
    AccountService accountService;
    @Autowired
    BaihocService baihocService;


    @GetMapping()
    public List<Test> getTest() {
        return testService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Test> getOneByID(@PathVariable("id") Integer id) {
        return testService.getOneById(id);
    }

    @PostMapping("")
    public Test create(@RequestBody Test test) {
        return testService.updatecode(test);
    } 

    @PutMapping("")
    public Test update(@RequestBody Test test) {
        return testService.updatecode(test);
    }   

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        testService.delete(id);
    }




    @GetMapping(value="/{username}/{idbaihoc}")
    public Test getMethodName(@PathVariable("username") String user, @PathVariable("idbaihoc") int idbaihoc) {
        return testService.getOneByUsernameAndBaihoc(user, idbaihoc);
    }
    

    @PostMapping("/run")
    public Test postTest(@RequestBody JsonNode test) {
        return testService.savecode(test);
    }

    @PutMapping("/{idbaihoc}/{username}")
    public Test updateTest(@RequestBody Test test, @PathVariable("idbaihoc") int idbaihoc,
    @PathVariable("username") String username ) {
        return testService.updatecode(test);
    }
    
}
