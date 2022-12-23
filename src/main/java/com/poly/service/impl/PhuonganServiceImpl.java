package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.bean.Phuongan;
import com.poly.dao.PhuonganDAO;
import com.poly.service.PhuonganService;

@Service
public class PhuonganServiceImpl implements PhuonganService {
    @Autowired
    PhuonganDAO phuonganDAO;

    @Override
    public List<Phuongan> getAllByIdQuiz(int idquiz) {
        return phuonganDAO.getAllByIdQuiz(idquiz);
    }

    @Override
    public List<Phuongan> getAll() {
        // TODO Auto-generated method stub
        return phuonganDAO.findAll();
    }

    @Override
    public Optional<Phuongan> getOneById(Integer id) {
        // TODO Auto-generated method stub
        return phuonganDAO.findById(id);
    }

    @Override
    public Phuongan create(Phuongan phuongan) {
        // TODO Auto-generated method stub
        return phuonganDAO.save(phuongan);
    }

    @Override
    public Phuongan update(Phuongan phuongan) {
        // TODO Auto-generated method stub
        return phuonganDAO.save(phuongan);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        phuonganDAO.deleteById(id);
    }


    
}
