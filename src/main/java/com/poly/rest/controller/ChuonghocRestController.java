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

import com.poly.bean.Chuonghoc;
import com.poly.service.ChuonghocService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/chuonghoc")
public class ChuonghocRestController {

    @Autowired
    ChuonghocService service;


    @GetMapping()
    public List<Chuonghoc> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public List<Chuonghoc> getOne(@PathVariable("id") Integer id){
        return service.getAll();
    }

    @PostMapping()
    public Chuonghoc create(@RequestBody Chuonghoc chuonghoc){
        return service.create(chuonghoc);
    }

    @PutMapping()
    public Chuonghoc update(@RequestBody Chuonghoc chuonghoc){
        return service.update(chuonghoc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

}