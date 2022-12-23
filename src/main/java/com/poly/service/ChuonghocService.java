package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Chuonghoc;


public interface ChuonghocService {
    
    List<Chuonghoc> getAllbyProduct(int id);

    List<Chuonghoc> getAll();

    Optional<Chuonghoc> getOne(Integer id);

    Chuonghoc create(Chuonghoc chuonghoc);

    Chuonghoc update(Chuonghoc chuonghoc);

    void delete(Integer id);
}
