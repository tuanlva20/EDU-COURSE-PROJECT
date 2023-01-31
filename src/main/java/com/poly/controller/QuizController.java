package com.poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.bean.Baihoc;
import com.poly.bean.Category;
import com.poly.bean.Chuonghoc;
// import com.poly.bean.DataBang;
import com.poly.bean.Lambai;
import com.poly.bean.Order;
import com.poly.bean.Quiz;
import com.poly.bean.Test;
import com.poly.dao.ChuonghocDAO;
import com.poly.dao.QuizDAO;
import com.poly.service.BaihocService;
import com.poly.service.ChuonghocService;
// import com.poly.service.DatabangService;
import com.poly.service.LambaiService;
import com.poly.service.OrderService;
import com.poly.service.QuizService;
import com.poly.service.TestService;

@Controller
public class QuizController {
    @Autowired
    ChuonghocService chuonghocService;
    @Autowired
    OrderService oService;
    @Autowired
    BaihocService baihocService;
    @Autowired
    QuizService quizService;
    @Autowired
    LambaiService lambaiService;

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    TestService testService;
    int idProductpublic;


    @RequestMapping(value = "/coursedetail/chuonghoc/{id}")
    public String baihoc(Model model, @PathVariable("id") int idProduct, HttpServletRequest request) {
        String username = request.getRemoteUser();
        if (username == null) {
            return "redirect:/security/login/form";
        } else {
            Order order = oService.damua(username, idProduct);
            List<Chuonghoc> list = chuonghocService.getAllbyProduct(idProduct);
            if (order !=null && order.isStatus() == true) {
                model.addAttribute("an", "disabled");
                model.addAttribute("quizService", quizService);
            }
            // DataBang bang = databangService.getDatabang(idProduct, username);
            // if(bang.getUsername() !=""){
            //     model.addAttribute("export", "hien");
            // }else{
            //     double progress = (double)  100 / bang.getSizeQuizByProduct() * bang.getSizeQuizBySucccess();

            //     model.addAttribute("progress",(double) Math.round(progress*100)/100+"%");
            //     model.addAttribute("export", "an");
            // }
            idProductpublic = idProduct;
            model.addAttribute("idproduct", idProduct);
            model.addAttribute("listProduct", list);
            return "quiz/solean";
        }

    }

    @RequestMapping(value = "/baihoc/details/{id}")
    public String baihocdetails(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        Baihoc baihoc = baihocService.getOneById(id);
        int idProductpublic = baihoc.getChuonghoc().getProduct().getId();
        Order order = oService.damua(username, idProductpublic);

        List<Baihoc> bh = baihocService.getAllByChuonghoc(baihoc.getChuonghoc().getId());
        if (username == null) {
            return "redirect:/security/login/form";
        }
        if (order != null) {
            model.addAttribute("baihoc", baihoc);
            return "quiz/exercise";
        } else {
            if (id == bh.get(0).getBaihocid()) {
                model.addAttribute("baihoc", baihoc);
                return "quiz/exercise";
            } else {
                return "redirect:/coursedetail/" + idProductpublic;
            }

        }

    }

    @RequestMapping("/baihoc/thuchanh/{idbaihoc}")
    public String thuchanh(@PathVariable("idbaihoc") int idbaihoc, Model model, 
    HttpServletRequest request) {
        String username = request.getRemoteUser();
        if (username == null) {
            return "redirect:/security/login/form";
        }
        Baihoc baihoc = baihocService.getOneById(idbaihoc);
        model.addAttribute("baihoc", baihoc);
        Test test = testService.getOneByUsernameAndBaihoc(request.getRemoteUser(), idbaihoc);
        if(test!=null){
            model.addAttribute("test", test);
        }
        String namengonngu = baihoc.getChuonghoc().getProduct().getCategory().getName();
        if (namengonngu.equals("HTML & CSS") ||
                namengonngu.equals("JavaScript")) {
            return "thuchanh/indexcode/indexhtml";
        } else if (namengonngu.equals("Java")) {
            return "thuchanh/indexcode/indexjava";
        } else {
            return "thuchanh/indexcode/indexC";
        }
    }

    @RequestMapping("bang/preview/download")
    public String downloadPDF(HttpServletRequest request, Model model){
        String username = request.getRemoteUser();
        int idkhoahoc = idProductpublic;
        model.addAttribute("username", username);
        model.addAttribute("idkhoahoc", idkhoahoc);
        return "bang/template_bang.html";
    }
}
