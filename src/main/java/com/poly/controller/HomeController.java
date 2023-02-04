package com.poly.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Product;
import com.poly.dao.OrderDAO;
import com.poly.dao.ProductDAO;
import com.poly.service.ProductService;


@Controller
public class HomeController {

    @Autowired
    ProductService pService;
    @Autowired
    OrderDAO odao;
    @Autowired
    ProductDAO pdao;
    
    @RequestMapping()
    public String index(Model model){
        List<Product> products = pService.getAll();
        model.addAttribute("products", products);
        return "course/index";
    }

    @RequestMapping({"/admin","/admin/home/index"})
    public String admin(Model model, HttpServletRequest rq){
        List<Integer> countPrice = odao.findOderByProductId();
        List<Product> listProduct = new ArrayList<>();
        for (int index = 0; index < 3; index++) {
            Optional<Product> pd = pdao.findById(countPrice.get(index));
            listProduct.add(pd.get());
        }
        model.addAttribute("listp", "aa");
        rq.setAttribute("ten", "aaaa");
        System.out.println("aaaa");
        return "redirect:/assets/admin/index.html";
    }
    
}
