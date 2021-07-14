package com.netcraker.model;



<<<<<<< HEAD
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

=======
>>>>>>> bdf28185b497989f95fe6c476d6f3df0fc69e28a
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "userscafe")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass = Role.class,fetch = FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private  Set<Role> roles;

<<<<<<< HEAD
    private boolean active;
=======
    private Role role;
>>>>>>> bdf28185b497989f95fe6c476d6f3df0fc69e28a

    @Column(name = "fullname")
    private String name;
    private String number;
    private String username;
    private String password;
    private String address;


<<<<<<< HEAD

=======
    public User(int id, Role role, String name, String number, String login, String password, String address) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.number = number;
        this.login = login;
        this.password = password;
        this.address = address;
    }
>>>>>>> bdf28185b497989f95fe6c476d6f3df0fc69e28a

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
=======
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
>>>>>>> bdf28185b497989f95fe6c476d6f3df0fc69e28a
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + roles +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", login='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
