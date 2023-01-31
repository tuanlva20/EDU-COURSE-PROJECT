package com.poly.rest.controller;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AccountDAO;
import com.poly.dao.OrderDAO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/thongke")
public class ThongkeRestController {
    
    @Autowired
    AccountDAO adao;
    @Autowired
    OrderDAO odao;
    HashMap<String, String> hasmapList = new HashMap<>();


    @GetMapping()
    public HashMap<String, String> getThongke(){

        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        hasmapList.put("month", String.valueOf(month));
        int countAccountByMonth = adao.getCountAccountByMonth(month);
        hasmapList.put("countByMonth", String.valueOf(countAccountByMonth));
        int countAllAccount = adao.getCountAllAccount();
        hasmapList.put("countAllAccount", String.valueOf(countAllAccount));
        int countOderByMonth = odao.totalOrderByMonth(month);
        hasmapList.put("totalOrderByMoth", String.valueOf(countOderByMonth));

        return hasmapList;

    }

}
