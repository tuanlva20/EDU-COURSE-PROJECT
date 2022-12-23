package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Thaoluan;
import com.poly.dao.ThaoluanDAO;
import com.poly.service.ThaoluanService;

@Service
public class ThaoluanServiceImpl implements ThaoluanService {

    @Autowired
    ThaoluanDAO thaoluanDAO;
    @Override
    public List<Thaoluan> getByBaihoc(int baihocid) {
        return thaoluanDAO.getByBaihoc(baihocid);
    }
    @Override
    public Thaoluan save(Thaoluan thaoluan) {
        return thaoluanDAO.save(thaoluan);
    }
    @Override
    public List<Thaoluan> getAll() {
        // TODO Auto-generated method stub
        return thaoluanDAO.findAll();
        }
    @Override
    public Optional<Thaoluan> getOneById(int id) {
        // TODO Auto-generated method stub
        return thaoluanDAO.findById(id);
    }
   
    @Override
    public Thaoluan create(Thaoluan Thaoluan) {
        // TODO Auto-generated method stub
        return thaoluanDAO.save(Thaoluan);
    }

    @Override
    public Thaoluan update(Thaoluan Thaoluan) {
        // TODO Auto-generated method stub
        return thaoluanDAO.save(Thaoluan);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        thaoluanDAO.deleteById(id);;
    }
    
}
