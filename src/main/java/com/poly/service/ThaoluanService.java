package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Thaoluan;

public interface ThaoluanService {
    
    List<Thaoluan> getByBaihoc(int baihocid);
    Thaoluan save(Thaoluan thaoluan);

    List<Thaoluan> getAll();
    Optional<Thaoluan> getOneById(int id);
    Thaoluan create(Thaoluan thaoluan);
    Thaoluan update(Thaoluan thaoluan);
    void delete(Integer id);
}
