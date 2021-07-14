package com.netcraker.services;

import com.netcraker.model.Role;
import com.netcraker.model.User;
import com.netcraker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
=======
import java.util.ArrayList;
>>>>>>> bdf28185b497989f95fe6c476d6f3df0fc69e28a
import java.util.List;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

    public List<User> findAll() {

<<<<<<< HEAD
        return userRepository.findAllUser();
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

=======
        Iterable it = userRepository.findAll();

        List users = new ArrayList<User>();
        it.forEach(e -> users.add(e));

        return users;
>>>>>>> bdf28185b497989f95fe6c476d6f3df0fc69e28a
    }

public void deleteUser(String username){
    userRepository.deletRolebyId(userRepository.findByUsername(username).getId());
    userRepository.deleteByUsername(username);
}

public String findRoleByUsername(String username){
        return userRepository.findByUsername(username).getRole().toString();
}



public boolean editUsername(User user,HttpServletRequest request){
    System.out.println(user);
    User userFromDBs = userRepository.findByUsername(user.getUsername());
    if (userFromDBs != null) {
        return false;
    }
        User userFromDB = userRepository.findByUsername(request.getUserPrincipal().getName());
    System.out.println(user);
    System.out.println(userFromDB);
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
