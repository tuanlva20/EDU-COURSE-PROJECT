package com.poly.service;

import java.util.List;

import com.poly.bean.Baihoc;

public interface BaihocService {
    List<Baihoc> getAll();
    Baihoc getOneById(int id);
    
    List<Baihoc> getAllByChuonghoc(int id);

    Baihoc create(Baihoc baihoc);

    Baihoc update(Baihoc baihoc);

    void delete(Integer id); 
}
