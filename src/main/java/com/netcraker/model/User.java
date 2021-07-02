package com.netcraker.model;



import javax.persistence.*;

@Entity
@Table(name = "userscafe")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private Role role;

    @Column(name="user_name")
    private String name;
    private String number;
    private String login;
    private String password;
    private String address;


    public User(int id, Role role, String name, String number, String login, String password, String address) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.number = number;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public User() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
