package com.netcraker.services;

import com.netcraker.model.Role;
import com.netcraker.model.User;
import com.netcraker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {


        @Autowired
        private UserRepository userRepository;

    public List<User> findAll() {
        Iterable it = userRepository.findAll();

        List users = new ArrayList<User>();
        it.forEach(e -> users.add(e));

        return users;
    }

    public boolean saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setRole(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepository.save(user);
        return true;

    }

public void blockOrUnblockUser(String username){
    User user = userRepository.findByUsername(username);
    if(user.getActive() == true)     user.setActive(false);
    else     user.setActive(true);
    userRepository.save(user);
}

public Set<Role> findRoleByUsername(String username){
        return userRepository.findByUsername(username).getRole();
}



public boolean editUsername(String login,HttpServletRequest request){
    if (userRepository.findByUsername(login) != null) {
        return false;
    }
        User userFromDB = userRepository.findByUsername(request.getUserPrincipal().getName());
        userFromDB.setUsername(login);
    userRepository.save(userFromDB);
    return true;
}

    public void editPassword(String password,HttpServletRequest request){
        User userFromDB = userRepository.findByUsername(request.getUserPrincipal().getName());
            userFromDB.setPassword(password);
        userRepository.save(userFromDB);

    }

    public void editData(@RequestBody User user, HttpServletRequest request){
        User userFromDB = userRepository.findByUsername(request.getUserPrincipal().getName());
        userFromDB.setAddress(user.getAddress());
        userFromDB.setName(user.getName());
        userFromDB.setNumber(user.getNumber());
        userRepository.save(userFromDB);
    }

}

