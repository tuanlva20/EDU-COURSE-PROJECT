package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Product;
import com.poly.dao.ProductDAO;
import com.poly.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDAO pdao;

    @Override
    public List<Product> getAll() {
        return pdao.findAll();
    }

    @Override
    public Product getOneById(int id) {
        return pdao.findById(id).get();
    }

    @Override
    public List<Product> getByCategoryId(Optional<Integer> id) {
        return pdao.findByCategoryId(id);
    }

    @Override
    public Product create(Product product) {
        return pdao.save(product);
    }

    @Override
    public Product update(Product product) {
        return pdao.save(product);
    }

    @Override
    public void delete(Integer id) {
        pdao.deleteById(id);
    }
}
