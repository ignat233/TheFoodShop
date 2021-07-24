package com.netcraker.repository;


import com.netcraker.model.Order;
import com.netcraker.model.ProductQty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Query(value = "SELECT * from orders o inner join userscafe u on o.user_id=u.id where username=?", nativeQuery = true)
    List<Order> findAllUsersOrders(String username);


}
