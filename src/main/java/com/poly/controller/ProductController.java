package com.poly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.poly.bean.Order;
import com.poly.bean.Product;
import com.poly.dao.AccountDAO;
import com.poly.service.OrderService;
import com.poly.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired
    ProductService pService;
    @Autowired
    OrderService oService;
    @Autowired
    AccountDAO accountDAO;

    @RequestMapping(value = "/courses")
    public String list(Model model,@RequestParam("cd") Optional<Integer> id){
        List<Product> products=new ArrayList<Product>();
        if(!id.isPresent()){
            products=pService.getAll();
        }else{
            products=pService.getByCategoryId(id);
        }
        model.addAttribute("products", products);
        return "course/courses";
    }
    
    @RequestMapping(value = "/coursedetail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, HttpServletRequest request){
        Order order;
        Product p=pService.getOneById(id);
        model.addAttribute("product", p);
        String username = request.getRemoteUser();
        if(username !=null && !accountDAO.existsById(username)){
            username = accountDAO.parseSubToUsername(username);
        }
        if(username != null && username.length() > 0){
                order = oService.damua(username,id);
                if(order!=null&& order.isStatus()==true){
                    model.addAttribute("an", "disabled");
                }
        }
        return "course/courses-details";
    }

    @RequestMapping(value="/courses/show")
    public String showCourse(Model model){
        return null;
    }
}
