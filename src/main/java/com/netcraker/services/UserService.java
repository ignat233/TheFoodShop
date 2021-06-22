package com.netcraker.services;

import com.netcraker.model.User;
import com.netcraker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {

//        Iterable it = userRepository.findAll();
//
//        List users = new ArrayList<User>();
//        it.forEach(e -> users.add(e));

        return userRepository.findAll();
    }


}
