package com.poly.rest.controller;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.bean.Account;
import com.poly.bean.Order;
import com.poly.bean.Product;
import com.poly.dao.AccountDAO;
import com.poly.dao.OrderDAO;
import com.poly.dao.ProductDAO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/thongke")
public class ThongkeRestController {
    
    @Autowired
    AccountDAO adao;
    @Autowired
    OrderDAO odao;
    @Autowired
    ProductDAO pdao;
    HashMap<String, Object> hasmapList = new HashMap<>();


    @GetMapping()
    public HashMap<String, Object> getThongke(){

        // try {
            LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int year = date.getYear();
        hasmapList.put("month", String.valueOf(month));
        hasmapList.put("year", String.valueOf(year));
        int countAccountByMonth = adao.getCountAccountByMonth(month);
        int countAllAccount = adao.getCountAllAccount();
        int countOderByMonth = odao.totalOrderByMonth(month, year);
        int countOderByYear = odao.totalOrderByYear(year);
        
        if (String.valueOf(countAccountByMonth).length() > 0) {
            hasmapList.put("countByMonth", String.valueOf(countAccountByMonth));
            if (String.valueOf(countAllAccount).length() > 0) {
                hasmapList.put("countAllAccount", String.valueOf(countAllAccount));
                if ( String.valueOf(countOderByMonth).length() > 0) {
                    hasmapList.put("totalOrderByMoth", String.valueOf(countOderByMonth));
                    if (String.valueOf(countOderByYear).length() > 0) {
                        hasmapList.put("totalOrderByYear", String.valueOf(countOderByYear));
                    } else hasmapList.put("totalOrderByYear", "");
                } else hasmapList.put("totalOrderByMoth", "");
            } else hasmapList.put("countAllAccount", "");
        } else hasmapList.put("countByMonth", "");
        List<Product> listProduct = pdao.findAll();
        List<Order> listOrder = odao.findAll();
        HashMap<String, Integer> chart = new HashMap<>();
        for(int i = 0; i < listProduct.size(); i++){
            int count = 0;
            for (int j = 0; j < listOrder.size(); j++) {
                if (listProduct.get(i).getId() == listOrder.get(j).getProduct().getId()) {
                    count ++;
                }
                if (j == listOrder.size()-1) {
                    chart.put(listProduct.get(i).getName(), count);
                }
            }
        }
        hasmapList.put("chart", chart);
        HashMap<Integer, Integer> monthInYear = new HashMap<>();
        for (int index = 1; index <= 12; index++) {
            int countAccount = adao.getCountAccountMonthInYear(index, year -1);
            monthInYear.put(index, countAccount);
        }
        hasmapList.put("monthInYear",monthInYear);
        // } catch (Exception e) {
        // }
        
        return hasmapList;

    }

}
