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

import com.poly.bean.Discount;
import com.poly.service.DiscountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/discount")
public class DiscountRestController {
    
    @Autowired
    DiscountService service;


    @GetMapping()
    public List<Discount> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public List<Discount> getOne(@PathVariable("id") Integer id){
        return service.getAll();
    }

    @PostMapping()
    public Discount create(@RequestBody Discount discount){
        return service.create(discount);
    }

    @PutMapping()
    public Discount update(@RequestBody Discount discount){
        return service.update(discount);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

}
