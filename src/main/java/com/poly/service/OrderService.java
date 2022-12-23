package com.poly.service;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.bean.Order;


public interface OrderService {

    Order create(JsonNode order);

    Order getById(Integer id);

    List<Order> getAll();

    List<Order> getByUsername(String username);
    
    Order damua(String username, int idProduct);

    List<Integer> findidOrders(String address, Date createdate, String email, String fullname,
    String phone, boolean status, String username);
   
   Integer findmax();
}
