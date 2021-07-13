package com.netcraker.controller;


import com.netcraker.model.Product;
import com.netcraker.model.Role;
import com.netcraker.model.User;
import com.netcraker.repository.UserRepository;
import com.netcraker.services.UserService;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String registration(Model model) {

        model.addAttribute("user",new User());

        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User user,Model model){
        if (!userService.saveUser(user)) {
               model.addAttribute("usernameError", "Пользователь с таким логином уже существует");
              return "register";
           }
        return "redirect:/login";

    }

    @GetMapping("/lk")
    public String lk(@Param("username") String username,HttpServletRequest request){

        if(userService.findRoleByUsername(request.getUserPrincipal().getName()).equals("[ADMIN]")){
            return "redirect:/admin";
        }
            return "redirect:/user";
    }

        @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
        public String lkUser(HttpServletRequest request,Model model){
        model.addAttribute("user",userRepository.findByUsername(request.getUserPrincipal().getName()));
        return "lkUser";
        }

        @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String lkAdmin(){
        return "lkAdmin";
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    @ResponseBody
    public List<User> showUsers(){
    return userService.findAll();
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody String username){
        userService.deleteUser(username);
        return "redirect:/admin";
    }

    @PostMapping("/editLogin")
    public String editUser1(@ModelAttribute User user,Model model,HttpServletRequest request){
        System.out.println(user);
        if (!userService.editUsername(user,request)) {
            model.addAttribute("Error", "Пользователь с таким логином уже существует");
            return "lkUser";
        }
      return "/index";

    }

    @PostMapping("/editPassword")
    public String editUser(@ModelAttribute User user,Model model,HttpServletRequest request){
        userService.editPassword(user,request);
        return "/login";

    }

    @PostMapping("/editData")
    public String editData(@ModelAttribute User user,Model model,HttpServletRequest request){
        userService.editData(user,request);
        return "redirect:/user";

    }





}


