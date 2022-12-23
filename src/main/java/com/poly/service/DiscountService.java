package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.bean.Discount;

public interface DiscountService {
    
    List<Discount> getAll();

    Optional<Discount> getOne(Integer id);

    Discount create(Discount discount);

    Discount update(Discount discount);

    void delete(Integer id);

}
