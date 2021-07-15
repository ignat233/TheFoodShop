package com.netcraker.services;

import com.netcraker.model.Role;
import com.netcraker.model.User;
import com.netcraker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }

        user.setRole(Collections.singleton(Role.USER));
        user.setActive(true);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;

    }

public void deleteUser(String username){
    userRepository.deleteById(userRepository.findByUsername(username).getId());
}

public Set<Role> findRoleByUsername(String username){
        return userRepository.findByUsername(username).getRole();
}



public boolean editUsername(User user,HttpServletRequest request){
    User userFromDBs = userRepository.findByUsername(user.getUsername());
    if (userFromDBs != null) {
        return false;
    }
        User userFromDB = userRepository.findByUsername(request.getUserPrincipal().getName());
        userFromDB.setUsername(user.getUsername());
    userRepository.save(userFromDB);
    return true;
}

    public void editPassword(User user,HttpServletRequest request){
        User userFromDB = userRepository.findByUsername(request.getUserPrincipal().getName());
            userFromDB.setPassword(user.getPassword());
        userRepository.save(userFromDB);

    }

    public void editData(User user,HttpServletRequest request){
        User userFromDB = userRepository.findByUsername(request.getUserPrincipal().getName());
        userFromDB.setAddress(user.getAddress());
        userFromDB.setName(user.getName());
        userFromDB.setNumber(user.getNumber());
        userRepository.save(userFromDB);
    }

}

