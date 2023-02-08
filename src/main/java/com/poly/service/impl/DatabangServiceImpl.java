package com.poly.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.service.AccountService;
import com.poly.service.BaihocService;
import com.poly.service.ProductService;
import com.poly.service.QuizService;
import com.poly.service.QuizServiceBean;
import com.poly.bean.DataBang;
import com.poly.service.DatabangService;
import com.poly.bean.Account;
import com.poly.bean.Baihoc;
import com.poly.bean.Chuonghoc;
import com.poly.bean.Lambai;
import com.poly.bean.Product;
@Service
public class DatabangServiceImpl implements DatabangService {

    @Autowired
    QuizServiceBean quizServiceBean;
    @Autowired
    QuizService quizService;
    @Autowired
    ProductService productService;
    @Autowired
    BaihocService baihocService;
    @Autowired
    AccountService accountService;

    @Override
    public DataBang getDatabang(int idproduct, String username) {
        int sizeQuizByProduct =0;
        int sizeQuizBySucccess = 0;
        Product product = productService.getOneById(idproduct);
        List<Chuonghoc> listChuonghocByProduct = product.getChuonghocs();
        for(int i =0 ; i <listChuonghocByProduct.size();i++){
            int idchuonghoc = listChuonghocByProduct.get(i).getId();
            List<Baihoc> listBaihocByChuonghoc = baihocService.getAllByChuonghoc(idchuonghoc);
            for(int j = 0;j<listBaihocByChuonghoc.size();j++){
                int idbaihoc = listBaihocByChuonghoc.get(j).getBaihocid();
                sizeQuizByProduct += quizService.getAllByBaihocId(idbaihoc).size();
                sizeQuizBySucccess += quizServiceBean.getAllDaxong(idproduct,username , idbaihoc).size();
            }
        }
        Account account = accountService.findByUsername(username);
        String name = account.getFullname();
        if(sizeQuizByProduct == sizeQuizBySucccess){
            
            DataBang data = new DataBang(name, product.getName(), new Date(),sizeQuizByProduct,sizeQuizBySucccess);
            return data;
        }else{
            DataBang data = new DataBang("", "", new Date(),sizeQuizByProduct,sizeQuizBySucccess);
            return data;
        }
    }
    
}
