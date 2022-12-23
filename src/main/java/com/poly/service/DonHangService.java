package com.poly.service;

import java.util.List;

import com.poly.bean.Donhang;

public interface DonHangService {

    List<Donhang> getalldonhang();


    Donhang update(Donhang donhang);

    void delete(Integer idOrder);

    

}