package com.poly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.bean.Order;
import com.poly.dao.OrderDAO;
import com.poly.service.OrderService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDAO orderDAO;

    @Override
    public Order create(Order orderData) {
        // ObjectMapper mapper=new ObjectMapper();
        // Order order=mapper.convertValue(orderData, Order.class);
        // orderDAO.save(order);

        // TypeReference<List<Orderdetail>> type=new TypeReference<List<Orderdetail>>(){};
        // List<Orderdetail> details=mapper.convertValue(orderData.get("orderdetails"), type)
        //     .stream()
        //     .peek(d -> d.setOrder(order)).collect(Collectors.toList());
        // ddao.saveAll(details);
        // return order;
        return orderDAO.save(orderData);
    }

    @Override
    public Optional<Order> getById(Integer id) {
        return orderDAO.findById(id);
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
    public Order update(Order order) {
        // TODO Auto-generated method stub
        return orderDAO.save(order);
    }

    @Override
    public void delete(Integer id) {
        orderDAO.deleteById(id);
    }

    
}
