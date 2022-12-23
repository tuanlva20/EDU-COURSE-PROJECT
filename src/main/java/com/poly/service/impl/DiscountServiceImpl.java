package com.poly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Discount;
import com.poly.dao.DiscountDAO;
import com.poly.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService{
    
    @Autowired
    DiscountDAO dao;

    @Override
    public List<Discount> getAll(){
        return dao.findAll();
    }

    @Override
    public Discount create(Discount discount) {
        discount.setCreatedate(new Date());
        return dao.save(discount);
    }

    @Override
    public Discount update(Discount discount) {
       
        return dao.save(discount);
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
        
    }

    @Override
    public Optional<Discount> getOne(Integer id) {
        
        return dao.findById(id);
    }


}
