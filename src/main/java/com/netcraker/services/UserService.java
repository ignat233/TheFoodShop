/*
 * Copyright
 */

package com.netcraker.services;

import com.netcraker.model.Role;
import com.netcraker.model.User;
import com.netcraker.repository.UserRepository;
import com.netcraker.services.method.FindAll;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * User Service.
 * Using for providing functionality to the user's capabilities.
 * The class extends the FindAll class.
 *
 * @since 0.0.1
 */
@Service
public class UserService extends FindAll<User, UserRepository> {

    /**
     * UserRepository field.
     */
    private final UserRepository repository;

    /**
     * Dependency injection through the constructor.
     *
     * @param repository User Repository
     */
    public UserService(final UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * The method saves the user to the database.
     *
     * @param user User
     * @return Boolean save
     */
    public boolean saveUser(final User user) {
        final boolean save;
        if (this.repository.findByUsername(user.getUsername()) == null) {
            user.setRole(Role.USER);
            user.setActive(true);
            this.repository.save(user);
            save = true;
        } else {
            save = false;
        }
        return save;
    }

    /**
     * Method is used to alter the user's activity, which allows the user to be blocked or unlocked.
     *
     * @param login Login
     */
    public void blockOrUnblockUser(final String login) {
        final User user = this.repository.findByUsername(login);
        user.setActive(!user.isActive());
        this.repository.save(user);
    }

    /**
     * The method finds the role of the user.
     *
     * @param login Login
     * @return Role
     */
    public Role findRoleByLogin(final String login) {
        return this.repository.findByUsername(login).getRole();
    }

    /**
     * The method changes the user's login.
     *
     * @param login Login
     * @param request Request
     * @return Boolean edit
     */
    public boolean editUsername(final String login, final HttpServletRequest request) {
        final boolean edit;
        if (this.repository.findByUsername(login) == null) {
            final User user = this.repository
                .findByUsername(request.getUserPrincipal().getName());
            user.setUsername(login);
            this.repository.save(user);
            edit = true;
        } else {
            edit = false;
        }
        return edit;
    }

    /**
     * The method changes the user's password.
     *
     * @param password Password
     * @param request Request
     */
    public void editPassword(final String password, final HttpServletRequest request) {
        final User user = this.repository.findByUsername(request.getUserPrincipal().getName());
        user.setPassword(password);
        this.repository.save(user);
    }

    /**
     * The method changes the user's data(address, name, number).
     *
     * @param user User
     * @param request Request
     */
    public void editData(final User user, final HttpServletRequest request) {
        final User usr = this.repository.findByUsername(request.getUserPrincipal().getName());
        usr.setAddress(user.getAddress());
        usr.setName(user.getName());
        usr.setNumber(user.getNumber());
        this.repository.save(usr);
    }

}
