/*
 * Copyright
 */

package com.netcraker.repository;

import com.netcraker.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository providing methods for User Services.
 *
 * @since 0.0.1
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * The method finds all users with the "User" role.
     *
     * @return All users
     */
    @Query(value = "SELECT * from userscafe  where role='USER'", nativeQuery = true)
    List<User> findAllUser();

    /**
     * The method finds user with the login.
     *
     * @param login Login
     * @return User
     */
    User findByUsername(String login);

}
