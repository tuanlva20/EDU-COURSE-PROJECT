package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Baihoc;
import com.poly.dao.BaihocDAO;
import com.poly.service.BaihocService;

@Service
public class BaihocServiceImpl implements BaihocService {

    @Autowired
    BaihocDAO baihocDAO;

    @Override
    public List<Baihoc> getAll() {
        return baihocDAO.findAll();
    }

    @Override
    public Baihoc getOneById(int id) {
        return baihocDAO.findById(id).get();
    }

    @Override
    public List<Baihoc> getAllByChuonghoc(int id) {
        // TODO Auto-generated method stub
        return baihocDAO.getListByIdChuonghoc(id);
    }

    @Override
    public Baihoc create(Baihoc baihoc) {
        // TODO Auto-generated method stub
        return baihocDAO.save(baihoc);
    }

    @Override
    public Baihoc update(Baihoc baihoc) {
        // TODO Auto-generated method stub
        return baihocDAO.save(baihoc);
    }

    @Override
    public void delete(Integer id) {
        baihocDAO.deleteById(id);
        
    }
    
}
