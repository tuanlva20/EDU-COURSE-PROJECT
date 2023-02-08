package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Chuonghoc;
import com.poly.dao.ChuonghocDAO;
import com.poly.service.ChuonghocService;

@Service
public class ChuonghocServiceImpl implements ChuonghocService {

    @Autowired
    ChuonghocDAO chuonghocDAO;

    // @Override
    // public List<Chuonghoc> getAll() {
    //     return chuonghocDAO.findAll();
    // }

    @Override
    public List<Chuonghoc> getAllbyProduct(int id) {
        return chuonghocDAO.getAllbyProduct(id);
    }

    @Override
    public List<Chuonghoc> getAll() {
        // TODO Auto-generated method stub
        return chuonghocDAO.findAll();
    }

    @Override
    public Optional<Chuonghoc> getOne(Integer id) {
        // TODO Auto-generated method stub
        return chuonghocDAO.findById(id);
    }

    @Override
    public Chuonghoc create(Chuonghoc chuonghoc) {
        // TODO Auto-generated method stub
        return chuonghocDAO.save(chuonghoc);
    }

    @Override
    public Chuonghoc update(Chuonghoc chuonghoc) {
        // TODO Auto-generated method stub
        return chuonghocDAO.save(chuonghoc);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        chuonghocDAO.deleteById(id);
    }
    
}
