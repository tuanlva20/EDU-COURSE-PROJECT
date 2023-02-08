package com.poly.bean;

import java.sql.Date;

import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Donhang {
    
    private Order order;
    private Orderdetail orderdetail;

}
