package com.poly.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.bean.Account;
import com.poly.bean.Baihoc;
import com.poly.bean.Chuonghoc;
// import com.poly.bean.DataBang;
import com.poly.bean.Lambai;
import com.poly.bean.Product;
import com.poly.bean.Quiz;
import com.poly.dao.QuizDAO;
import com.poly.service.AccountService;
import com.poly.service.BaihocService;
// import com.poly.service.DatabangService;
import com.poly.service.ProductService;
import com.poly.service.QuizService;
import com.poly.service.QuizServiceBean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin("*")
@RequestMapping("/rest/export")
public class ExportPdfController {
    
    
    // @Autowired
    // DatabangService databangService;
    // @GetMapping(value="{idproduct}/{username}")
    // public DataBang getMethodName(@PathVariable("idproduct") int idproduct,
    // @PathVariable("username") String username ){
    //     return databangService.getDatabang(idproduct, username);
    // }
    
}
