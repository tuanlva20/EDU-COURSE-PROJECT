package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Phuongan;

public interface PhuonganService {
    
    List<Phuongan> getAllByIdQuiz(int idquiz);
    List<Phuongan> getAll();
    Optional<Phuongan> getOneById(Integer id);
    Phuongan create(Phuongan phuongan);
    Phuongan update(Phuongan phuongan);
    void delete(Integer id);
}
