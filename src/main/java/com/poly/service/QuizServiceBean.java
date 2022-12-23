package com.poly.service;

import java.util.List;

import com.poly.bean.Baihoc;
import com.poly.bean.Quiz;

public interface QuizServiceBean {
    List<Quiz> getAllDaxong(int productid,String username,int idbaihoc);
    List<Quiz> getAllByBaihocId(int idbaihoc);
    List<Baihoc> getAllChuaxong(int productid,int idchuonghoc,String username);
}
