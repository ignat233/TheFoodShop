/*
 * Copyright
 */

package com.netcraker.repository;

import com.netcraker.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository providing methods for Order Services.
 *
 * @since 0.0.1
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    /**
     * The method finds all orders from a user with the login.
     *
     * @param login Login
     * @return All orders
     */
    @Query(nativeQuery = true,
        value = "SELECT * from orders o inner join userscafe u on o.user_id=u.id where login=?")
    List<Order> findAllOrdersOfUser(String login);

}
