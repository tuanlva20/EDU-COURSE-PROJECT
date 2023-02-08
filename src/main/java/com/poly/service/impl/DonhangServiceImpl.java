package com.poly.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.poly.bean.Donhang;
import com.poly.bean.Order;
import com.poly.bean.Orderdetail;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.service.DonHangService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class  DonhangServiceImpl implements DonHangService{
    @Autowired
     OrderDAO orderDAO;

    @Autowired
    OrderDetailDAO orderdetailDAO;

    List<Order> listOrder;
    List<Orderdetail> listOrderdetail;
    List<Donhang> listDonhang;
    Donhang dh;

    @Override
    public List<Donhang> getalldonhang() {
        listDonhang = new ArrayList<>();
        listOrder = orderDAO.findAll();
        listOrderdetail = orderdetailDAO.findAll();
        if(listOrder.size() > 0 && listOrderdetail.size() > 0){
            
            for (Orderdetail od : listOrderdetail) {
                for (Order o : listOrder) {
                   
                    if (od.getOrder().getId() == o.getId()){
                        dh = new Donhang(o,od);
                        listDonhang.add(dh);
                    }
                }
            }
        }
        return listDonhang;
    }

    // @Override
    // public Donhang create(Donhang donhang) {
    //     orderDAO.save(donhang.getOrder());
    //     donhang.getOrderdetail().getOrder().setId(orderDAO.findmax());
    //     orderdetailDAO.save(donhang.getOrderdetail());
    //     return donhang;

    // }

    @Override
    public Donhang update(Donhang donhang){
        orderDAO.save(donhang.getOrder());
        orderdetailDAO.save(donhang.getOrderdetail());
        return donhang;
    }

    @Override
    public void delete(Integer idOrder){
        List<Orderdetail> orderdetailId = orderdetailDAO.findByOrderId(idOrder);
        for (Orderdetail orderdetail : orderdetailId) {
            orderdetailDAO.deleteById(orderdetail.getId());
        }
        
        orderDAO.deleteById(idOrder);
    }

}
