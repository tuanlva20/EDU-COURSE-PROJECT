package com.poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Product;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("")
    public String Index(Model model, HttpServletRequest rq){
        List<Product> pList = new ArrayList();
        String username = rq.getRemoteUser();
        // odList = odDAO.getCoursePayByUsername(username);
        // if(odList.size() > 0){
        //     odList.forEach(item -> pList.add(item.getProduct()));
        // }
        model.addAttribute("products", pList);
        return "usertemp/user";
    }

    @RequestMapping("/active")
    public String active(Model model){
        
        return "forward:/user";
    }
}
