package com.poly.rest.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Order;
import com.poly.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/order")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping("")
    public Order postOrder(@RequestBody JsonNode order){
        return orderService.create(order);
    }
    @GetMapping("")
    public List<Order> getOrder(){
        return orderService.getAll();
    }
}
