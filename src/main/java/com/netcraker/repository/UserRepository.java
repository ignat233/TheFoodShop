package com.netcraker.repository;

import com.netcraker.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * from userscafe u inner join user_role ur on u.id=ur.user_id where roles='USER'", nativeQuery = true)
    List<User> findAllUser();

    @Query(value = "SELECT * from userscafe  where username=?1", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "DELETE from user_role where user_id=?1", nativeQuery = true)
    void deletRolebyId(Long id);

    @Query(value = "DELETE from userscafe where username=?1", nativeQuery = true)
    void deleteByUsername(String username);




}
