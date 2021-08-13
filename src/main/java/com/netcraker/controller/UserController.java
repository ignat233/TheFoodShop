/*
 * Copyright
 */

package com.netcraker.controller;

import com.netcraker.controller.massage.Massage;
import com.netcraker.model.Product;
import com.netcraker.model.Role;
import com.netcraker.model.User;
import com.netcraker.repository.UserRepository;
import com.netcraker.services.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for User model.
 *
 * @since 0.0.1
 * Methods are allowed to have more than one return.
 * Allow duplicate literals.
 */
@Controller
@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.OnlyOneReturn"})
public class UserController {

    /**
     * UserService field.
     */
    @Autowired
    private UserService service;

    /**
     * UserRepository field.
     */
    @Autowired
    private UserRepository repository;

    /**
     * Get registration window.
     *
     * @param model Model
     * @return Register.html
     */
    @GetMapping("/register")
    public static String registration(final Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * User registration and saving.If registration was not successful return register.html
     * with massage,else redirect:/login.
     *
     * @param user User
     * @param model Model
     * @return Register.html or redirect:/login.
     * @checkstyle ReturnCountCheck (5 lines) The method is allowed to have more than one return.
     */
    @PostMapping("/register")
    public String addUser(@ModelAttribute final User user, final Model model) {
        if (!this.service.saveUser(user)) {
            model.addAttribute("usernameError", Massage.LOGIN_ERROR.getMassage());
            return "register";
        }
        return "redirect:/login";
    }

    /**
     * Get personal account.If user don't sign in redirect:/login, if user sign in with Admin role
     * redirect:/admin, if user sign in with User role redirect:/user.
     *
     * @param request Request
     * @return Redirect:/login or redirect:/admin or redirect:/user
     * @checkstyle ReturnCountCheck (5 lines) The method is allowed to have more than one return.
     */
    @GetMapping("/lk")
    public String lkRedirect(final HttpServletRequest request) {
        if (this.repository.findByUsername(request.getUserPrincipal().getName()) == null) {
            return "redirect:/login";
        }
        if (this.service.findRoleByLogin(request.getUserPrincipal().getName()).equals(Role.ADMIN)) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    /**
     * Get the user's personal account.
     *
     * @return LkUser.html
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public static String lkUser() {
        return "lkUser";
    }

    /**
     * Get the adman's personal account.
     *
     * @param model Model
     * @return LkAdmin.html
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public static String lkAdmin(final Model model) {
        model.addAttribute("product", new Product());
        return "lkAdmin";
    }

    /**
     * Get all user with User role.
     *
     * @return All user with User role.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    @ResponseBody
    public List<User> showUsers() {
        return this.repository.findAllUser();
    }

    /**
     * Block or unblock a user with the login.
     *
     * @param login Login
     */
    @PostMapping("/block")
    @ResponseBody
    public void blockUser(@RequestBody final String login) {
        this.service.blockOrUnblockUser(login);
    }

    /**
     * Changing login.
     *
     * @param login Login
     * @param model Model
     * @param request Request
     * @return If the login is changed then redirect:/login, else return lkUser.html
     * @checkstyle ReturnCountCheck (5 lines) The method is allowed to have more than one return.
     */
    @PostMapping("/editLogin")
    public String editUserLogin(
        @RequestParam(required = false, name = "login") final String login,
        final Model model, final HttpServletRequest request) {
        if (!this.service.editUsername(login, request)) {
            model.addAttribute("usernameError", Massage.LOGIN_ERROR.getMassage());
            return "lkUser";
        }
        return "redirect:/login";
    }

    /**
     * Get the user logged in.
     *
     * @param request Request
     * @return User
     * @checkstyle ReturnCountCheck (5 lines) The method is allowed to have more than one return.
     */
    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(final HttpServletRequest request) {
        if (request.getUserPrincipal() == null) {
            return new User();
        }
        return this.repository.findByUsername(request.getUserPrincipal().getName());
    }

    /**
     * Changing user password.
     *
     * @param password Password
     * @param model Model
     * @param request Request
     * @return LkUser.html
     */
    @PostMapping("/editPassword")
    public String editUserPassword(
        @RequestParam(required = false, name = "password") final String password,
        final Model model, final HttpServletRequest request) {
        this.service.editPassword(password, request);
        model.addAttribute("passwordMassage", Massage.PASSWORD_MASSAGE.getMassage());
        return "lkUser";
    }

    /**
     * Changing user data.
     *
     * @param user User
     * @param request Request
     * @return LkUser.html
     */
    @PostMapping("/editData")
    public String editData(@RequestBody final User user, final HttpServletRequest request) {
        this.service.editData(user, request);
        return "lkUser";
    }
}
