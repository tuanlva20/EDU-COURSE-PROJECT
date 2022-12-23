package com.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.bean.Discount;

public interface DiscountDAO extends JpaRepository<Discount, Integer>{

}