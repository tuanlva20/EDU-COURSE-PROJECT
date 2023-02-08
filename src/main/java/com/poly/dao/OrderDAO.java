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

    @Query(value="select * from orderdetails,orders where orderdetails.orderid = orders.id and username = ?1 and productid = ?2",
    nativeQuery = true)
    Order damua(String username, int idProduct);

    @Query(value = "select o.id from orders o where o.address=?1 and o.createdate=?2 and o.email=?3 and o.fullname=?4 and o.phone=?5 and"+
    " o.status=?6 and o.username=?7", nativeQuery = true)
    List<Integer> findidOrders(String address,Date createdate, String email, String fullname, String phone, boolean status, String username);

    @Query(value="select * from orders o, orderdetails od where o.id=od.orderid and od.price = ?1 and o.username = ?2",
    nativeQuery = true)
    Order updateStatusOrder(Integer price, String username);

    @Query(value = "select sum(price) as totalprice from orders od where od.status = 1"
    +   " and month(od.createdate) = ?1 and year(od.createdate) = ?2", nativeQuery = true)
    Integer totalOrderByMonth(int month, int year);

    @Query(value = "select sum(price) as totalprice from orders od where od.status = 1 and year(od.createdate) = ?", nativeQuery = true)
    Integer totalOrderByYear(int year);

    @Query(value = "select od.product.id  from Order od, Product p where od.product.id = p.id")
    List<Integer> findOderByProductId();

    @Query(value = "select SUM(price) as totalprice from Order od where od.status = 1 and od.product.id = ?1")
    Integer totalOrderByProductId(int id);

}
