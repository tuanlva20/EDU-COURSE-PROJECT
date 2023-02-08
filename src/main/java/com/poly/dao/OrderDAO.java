package com.poly.dao;

import java.util.Date;
import java.util.List;

import com.poly.bean.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDAO extends JpaRepository<Order, Integer>{

    @Query("select o from Order o where o.account.id=?1")
    List<Order> findByUsernameLike(String username);
    
    @Query(value="select max(o.id) from orders o", nativeQuery = true)
    Integer findmax();

    @Query(value="select * from orderdetails od,orders o where od.orderid = o.id and o.username = ?1 and od.productid = ?2",
    nativeQuery = true)
    Order damua(String username, int idProduct);


        @Query(value = "select o.id from orders o where o.address=?1 and o.createdate=?2 and o.email=?3 and o.fullname=?4 and o.phone=?5 and"+
        " o.status=?6 and o.username=?7", nativeQuery = true)
        List<Integer> findidOrders(String address,Date createdate, String email, String fullname, String phone, boolean status, String username);



    @Query(value="select * from orders o, orderdetails od where o.id=od.orderid and o.id = ?1 and o.username = ?2",
    nativeQuery = true)
    Order updateStatusOrder(String id,String username);


}
