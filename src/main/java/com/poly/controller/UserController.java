package com.poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Orderdetail;
import com.poly.bean.Product;
import com.poly.dao.AccountDAO;
import com.poly.dao.OrderDetailDAO;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    OrderDetailDAO odDAO;

    @Autowired
    AccountDAO accountDAO;

    @RequestMapping("")
    public String Index(Model model, HttpServletRequest rq){
        List<Orderdetail> odList = new ArrayList<>();
        List<Product> pList = new ArrayList();
        String username = rq.getRemoteUser();
        if (!accountDAO.existsById(username)) {
            username = accountDAO.parseSubToUsername(username);
        }
        odList = odDAO.getCoursePayByUsername(username);
        if(odList.size() > 0){
            odList.forEach(item -> pList.add(item.getProduct()));
        }
        model.addAttribute("products", pList);
        return "usertemp/user";
    }

    @RequestMapping("/active")
    public String active(Model model){
        
        return "forward:/user";
    }


}
