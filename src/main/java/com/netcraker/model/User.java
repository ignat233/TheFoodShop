/*
 * Copyright
 */

package com.netcraker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class describing the user of the application.
 *
 * @since 0.0.1
 */
@Entity
@Getter
@Setter
@Table(name = "userscafe")
public class User {

    /**
     * Individual user number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * User activity. True allows logging in, false denies.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * User activity.
     */
    private boolean active;

    /**
     * User name.
     */
    @Column(name = "fullname")
    private String name;

    /**
     * Phone number.
     */
    private String number;

    /**
     * Login(username).
     */
    @Column(name = "login")
    private String username;

    /**
     * Login password.
     */
    private String password;

    /**
     * User address.
     */
    private String address;

}
