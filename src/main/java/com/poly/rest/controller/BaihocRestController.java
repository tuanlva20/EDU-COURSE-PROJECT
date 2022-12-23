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

import com.poly.bean.Baihoc;
import com.poly.service.BaihocService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/baihoc")
public class BaihocRestController { 

    @Autowired
    BaihocService service;



    @GetMapping("/listquiz/{id}")
    public Baihoc getOneByid(@PathVariable("id") Integer id){
        return service.getOneById(id);
    }


    @GetMapping()
    public List<Baihoc> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public List<Baihoc> getOne(@PathVariable("id") Integer id){
        return service.getAll();
    }

    @PostMapping()
    public Baihoc create(@RequestBody Baihoc baihoc){
        return service.create(baihoc);
    }

    @PutMapping()
    public Baihoc update(@RequestBody Baihoc baihoc){
        return service.update(baihoc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

}

