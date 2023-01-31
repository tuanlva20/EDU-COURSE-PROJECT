package com.poly.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.bean.Order;


public interface OrderService {

    Order create(Order order);
    Order update(Order order);
    void delete(Integer id);

    Optional<Order> getById(Integer id);

    List<Order> getAll();

    List<Order> getByUsername(String username);
    
    Order damua(String username, int idProduct);
}
