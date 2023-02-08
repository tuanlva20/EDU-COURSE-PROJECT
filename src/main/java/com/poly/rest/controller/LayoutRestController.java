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
        List<Integer> listOrderId = odao.findOderByProductId();
        List<Integer> listTotalPriceOfOrderByOrderId = new ArrayList<>();
        List<Product> listProduct = new ArrayList<>();
        Map<Integer, Integer> hashmapOrderId = new HashMap<>();
        for (int i = 0; i < listOrderId.size(); i++) {
            int countPrice = odao.totalOrderByProductId(listOrderId.get(i));
            listTotalPriceOfOrderByOrderId.add(countPrice);
            hashmapOrderId.put(listOrderId.get(i), countPrice);
        }

        hashmap.put("totalPriceOfOrderByOrderId", hashmapOrderId);
        
        int c = 0;
        for (Integer orderId : hashmapOrderId.keySet()) {
            if (c == 3) {
                break;
            }
            Optional<Product> product = pdao.findById(orderId);
            listProduct.add(product.get());
            c ++;
        }
        hashmap.put("products", listProduct);
        return hashmap;
    }

}
