package com.poly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Order;
import com.poly.bean.Orderdetail;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.service.OrderService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDetailDAO ddao;

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper=new ObjectMapper();
        Order order=mapper.convertValue(orderData, Order.class);
        orderDAO.save(order);

        TypeReference<List<Orderdetail>> type=new TypeReference<List<Orderdetail>>(){};
        List<Orderdetail> details=mapper.convertValue(orderData.get("orderdetails"), type)
            .stream()
            .peek(d -> d.setOrder(order)).collect(Collectors.toList());
        ddao.saveAll(details);
        return order;
    }

    @Override
    public Order getById(Integer id) {
        return orderDAO.findById(id).get();
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    @Override
    public List<Order> getByUsername(String username) {
        return orderDAO.findByUsernameLike(username);
    }

    @Override
    public Order damua(String username, int idProduct) {
        return orderDAO.damua(username, idProduct);
    }

   
    @Override
    public Integer findmax() {
        return orderDAO.findmax();
    }
    
    @Override
    public List<Integer> findidOrders(String address,  Date createdate, String email, String fullname, String phone,
            boolean status, String username) {
        // TODO Auto-generated method stub
        return orderDAO.findidOrders(address, createdate, email, fullname, phone, status, username);
    }
    
}
