package com.poly.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Order;
import com.poly.service.OrderService;

import java.util.List;
import java.util.Optional;

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

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/bill")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping("")
    public Order postOrder(@RequestBody Order order){
        return orderService.create(order);
    }
    @GetMapping("")
    public List<Order> getAll(){
        return orderService.getAll();
    }


    @GetMapping("{id}")
    public Optional<Order> getOne(@PathVariable("id") Integer id){
        return orderService.getById(id);
    }

    @PutMapping("")
    public Order update(@RequestBody Order order){
        return orderService.update(order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        orderService.delete(id);
    }
}
