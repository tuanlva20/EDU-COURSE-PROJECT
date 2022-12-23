package com.poly.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.bean.Orderdetail;

public interface OrderDetailDAO extends JpaRepository<Orderdetail, Integer>{
    

    @Query(value="select * from orderdetails od left outer join orders o on o.id=od.orderid left outer join products p on p.id=od.productid where o.username=?1", nativeQuery = true)
    List<Orderdetail> getCoursePayByUsername(String username);

    @Query("select o from Orderdetail o where o.order.id=?1")
    List<Orderdetail> findByOrderId(Integer id);

    @Query("select o from Orderdetail o where o.product.id=?1")
    List<Orderdetail> findByProductId(Optional<Integer> id);
}
