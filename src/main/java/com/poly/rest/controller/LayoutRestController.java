package com.poly.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.bean.Product;
import com.poly.dao.OrderDAO;
import com.poly.dao.ProductDAO;

@RestController
@CrossOrigin("*")
@RequestMapping("rest/layout")
public class LayoutRestController {
    
    @Autowired
    OrderDAO odao;
    @Autowired
    ProductDAO pdao;
    HashMap<String, Object> hashmap = new HashMap<>();

    @GetMapping()
    public HashMap<String, Object> init(){
        List<Integer> countPrice = odao.findOderByProductId();
        List<Product> listProduct = new ArrayList<>();
        Map<Integer, Integer> countP = new HashMap<>();
        for (int i = 0; i < countPrice.size(); i++) {
            countP.put(countPrice.get(i), i);
        }
        hashmap.put("countprice", countP);
        for (int index = 0; index < 3; index++) {
            Optional<Product> pd = pdao.findById(countPrice.get(index));
            listProduct.add(pd.get());
        }
        hashmap.put("product", listProduct);
        return hashmap;
    }

}
