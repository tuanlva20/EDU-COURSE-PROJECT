
package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.bean.Lambai;

import com.poly.service.LambaiService;
@RestController
@CrossOrigin("*")
@RequestMapping("/rest/lambai")
public class LambaiRestController {
    
    @Autowired
    LambaiService lambaiService;
    @PostMapping("")
    public Lambai postLambai(@RequestBody JsonNode lambai) {
        return lambaiService.create(lambai);
    }

    @GetMapping(value="/{username}/{quizid}")
    public Lambai getLambaibyUser(@PathVariable("username") String username,
    @PathVariable("quizid") int quizid) {
        return lambaiService.getAllbyUserAndQuiz(username,quizid);
    }
}
