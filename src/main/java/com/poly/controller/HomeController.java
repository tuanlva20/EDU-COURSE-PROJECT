package com.poly.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Product;
import com.poly.service.ProductService;


@Controller
public class HomeController {
    @Autowired
    ProductService pService;
    @RequestMapping()
    public String index(Model model){
        List<Product> products = pService.getAll();
        model.addAttribute("products", products);
        System.out.println("haha");
        return "course/index";
    }

    @RequestMapping({"/admin","/admin/home/index"})
    public String admin(){
        return "redirect:/assets/admin/index.html";
    }

    
}
