package com.poly.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.poly.bean.Order;
import com.poly.dao.AccountDAO;
import com.poly.dao.OrderDAO;
import com.poly.service.MailService;
import com.poly.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDAO oDao;
    @Autowired
    MailService mService;
    @Autowired
    AccountDAO accountDAO;
    @RequestMapping(value = "/order/list")
    public String listOrder(Model model, HttpServletRequest request){
        String username=request.getRemoteUser();
        model.addAttribute("orders", orderService.getByUsername(username));
        return "order/list";
    }

    @RequestMapping(value = "/order/checkout")
    public String checkout(){
        return "order/checkout";
    }

    @RequestMapping(value = "/order/detail/{id}")
    public String orderDetail(Model model, @PathVariable("id") Integer id){
        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        return "order/detail";
    }

    @RequestMapping(value = "/order/detail")
    public String orderDetail(Model model,
                            @RequestParam("vnp_Amount") String paymentCount,
                            @RequestParam("vnp_CardType") String vnp_CardType,
                            @RequestParam("vnp_BankCode") String vnp_BankCode,
                            @RequestParam("vnp_TransactionNo") String vnp_TransactionNo,
                            @RequestParam("vnp_TxnRef") String vnp_TxnRef,
                            @RequestParam("vnp_OrderInfo") String vnp_OrderInfo,  HttpServletRequest req){
        if (Integer.parseInt(vnp_TransactionNo) == 0) {
            return "redirect:/courses";
        }else{
            String username = req.getRemoteUser();
            if(!accountDAO.existsById(username)){
                username = accountDAO.parseSubToUsername(username);
            }
            // Order order = oDao.updateStatusOrder(vnp_TxnRef,username);
            int orderId = Integer.parseInt(vnp_TxnRef);
            Optional<Order> order = oDao.findById(orderId);
            Order orderTest = order.get();
            order.get().setStatus(true);
            oDao.save(order.get());
            model.addAttribute("order", order.get());
            // model.addAttribute("paymentPrice", Integer.parseInt(paymentCount));
            // model.addAttribute("vnp_CardType", vnp_CardType);
            // model.addAttribute("vnp_BankCode", vnp_BankCode);
            // model.addAttribute("vnp_OrderInfo", vnp_OrderInfo);
            mService.sendMail();
            return "order/detail"; 
        }
    }

}
