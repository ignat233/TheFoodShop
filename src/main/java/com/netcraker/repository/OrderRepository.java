package com.netcraker.repository;


import com.netcraker.model.Order;
import com.netcraker.model.ProductQuantity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Query(value = "SELECT * from orders o inner join userscafe u on o.user_id=u.id where login=?", nativeQuery = true)
    List<Order> findAllUsersOrders(String login);



}
