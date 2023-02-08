package com.poly.controller;


import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        System.out.println("aaaaaa");
        return "course/index";
    }

    @RequestMapping({"/admin","/admin/home/index"})
    public String admin(Model model, HttpServletRequest rq){

        return "redirect:/assets/admin/index.html";
    }

    // @RequestMapping({"/admin","/admin/home/index"})
    // public void admin(Model model, HttpServletRequest rq, HttpServletResponse rs){
    //     PublicKey publicKey = null;
    //     PrivateKey privateKey = null;
    //     String strEncrypt = null;
    //     try {
	// 		SecureRandom sr = new SecureRandom();
	// 		// Thuật toán phát sinh khóa - RSA
	// 		// Độ dài khóa 1024(bits), độ dài khóa này quyết định đến độ an toàn của khóa, càng lớn thì càng an toàn
    //         // Demo chỉ sử dụng 1024 bit. Nhưng theo khuyến cáo thì độ dài khóa nên tối thiểu là 2048
	// 		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
	// 		kpg.initialize(1024, sr);

	// 		// Khởi tạo cặp khóa
	// 		KeyPair kp = kpg.genKeyPair();
	// 		// PublicKey
	// 		publicKey = kp.getPublic();
	// 		// PrivateKey
	// 		privateKey = kp.getPrivate();

	// 		System.out.println("Generate key successfully");
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
    //     //ma hoa du lieu
    //     try {

	// 		// Mã hoá dữ liệu
	// 		Cipher c = Cipher.getInstance("RSA");
	// 		c.init(Cipher.ENCRYPT_MODE, publicKey);
	// 		String msg = "123456";
	// 		byte encryptOut[] = c.doFinal(msg.getBytes());
	// 		strEncrypt = Base64.getEncoder().encodeToString(encryptOut);
	// 		System.out.println("Chuỗi sau khi mã hoá: " + strEncrypt);

	// 	} catch (Exception ex) {
	// 		ex.printStackTrace();
	// 	}

    //     //giai ma
    //     try {
	// 		// Giải mã dữ liệu
	// 		Cipher c = Cipher.getInstance("RSA");
	// 		c.init(Cipher.DECRYPT_MODE, privateKey);
	// 		byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(strEncrypt));
	// 		System.out.println("Dữ liệu sau khi giải mã: " + new String(decryptOut));
	// 	} catch (Exception ex) {
	// 		ex.printStackTrace();
	// 	}
        
    // }
    
}
