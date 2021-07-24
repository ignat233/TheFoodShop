package com.netcraker.controller;


import com.netcraker.model.Product;
import com.netcraker.model.Role;
import com.netcraker.model.User;
import com.netcraker.repository.UserRepository;
import com.netcraker.services.ProductService;
import com.netcraker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/register")
    public String registration(Model model) {

        model.addAttribute("user",new User());

        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute User user,Model model){
        if (!userService.saveUser(user)) {
            String usernameError = "Пользователь с таким логином уже существует";
               model.addAttribute("usernameError", usernameError);
              return "register";
           }
        return "redirect:/login";

    }



    @GetMapping("/lk")
    public String lk(HttpServletRequest request){
        if(userService.findRoleByUsername(request.getUserPrincipal().getName()).equals(Collections.singleton(Role.ADMIN))) {
            return "redirect:/admin";
        }
            return "redirect:/user";
    }

//    @GetMapping("/lkData")
//    public String lkData(HttpServletRequest request,Model model){
//        model.addAttribute("user",userRepository.findByUsername(request.getUserPrincipal().getName()));
//        return "lkUser";
//    }

        @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
        public String lkUser(HttpServletRequest request,Model model){
        model.addAttribute("user",userRepository.findByUsername(request.getUserPrincipal().getName()));
        return "lkUser";
        }

        @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String lkAdmin(Model model){
        model.addAttribute("product",new Product());
        return "lkAdmin";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    @ResponseBody
    public List<User> showUsers(){
    return userRepository.findAllUser();
    }

    @PostMapping("/block")
    @ResponseBody
    public void blockUser(@RequestBody String username){
        userService.blockOrUnblockUser(username);
    }

    @PostMapping("/editLogin")
    public String editUserLogin(@ModelAttribute User user,Model model,HttpServletRequest request){
        if(!userService.editUsername(user,request)){
        String usernameError = "Пользователь с таким логином уже существует";
        model.addAttribute("usernameError", usernameError);
        return "lkUser";
    }
      return "redirect:/login";

    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(HttpServletRequest request){
        if(request.getUserPrincipal()==null) return null;
        return userRepository.findByUsername(request.getUserPrincipal().getName());
    }



    @PostMapping("/editPassword")
    public String editUserPassword(@ModelAttribute User user,Model model,HttpServletRequest request){
        userService.editPassword(user,request);
        return "redirect:/login";

    }

    @PostMapping("/editData")
    public String editData(@ModelAttribute User user,HttpServletRequest request){
        userService.editData(user,request);
        return "redirect:/user";
    }
}


