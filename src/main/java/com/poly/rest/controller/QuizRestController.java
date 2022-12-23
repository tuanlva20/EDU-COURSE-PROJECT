package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.poly.bean.Account;
import com.poly.bean.Baihoc;
import com.poly.bean.Lambai;
import com.poly.bean.Phuongan;
import com.poly.bean.Quiz;
import com.poly.bean.Test;
import com.poly.service.AccountService;
import com.poly.service.BaihocService;
import com.poly.service.LambaiService;
import com.poly.service.PhuonganService;
import com.poly.service.QuizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/quiz")
public class QuizRestController {
    
    @Autowired
    QuizService quizService;


    @Autowired
    AccountService accountService;

   

   

    // @GetMapping(value="/quiz/{id}")
    // public Quiz getQuizbyid(@PathVariable("id") int id) {
    //     return quizService.getQuizbyId(id);
    // }

    @GetMapping()
    public List<Quiz> getAll(){
        return quizService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Quiz> getOne(@PathVariable("id") Integer id){
        return quizService.getOneById(id);
    }

    @PostMapping("")
    public Quiz create(@RequestBody Quiz Quiz){
        return quizService.create(Quiz);
    }

    @PutMapping("")
    public Quiz update(@RequestBody Quiz Quiz){
        return quizService.update(Quiz);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        quizService.delete(id);
    }

    @PutMapping("/update/{username}")
    public Account updateDiem(@RequestBody Account account,@PathVariable("username") String username){
        return accountService.update1(account);
    }
    
    
}
