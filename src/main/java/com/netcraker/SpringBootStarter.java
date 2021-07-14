package com.netcraker;

import com.netcraker.model.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringBootStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class,args);

    }

//    public static void main(String[] args) {
//        System.out.println(Role.ADMIN.toString());
//        System.out.println(Role.ADMIN.getAuthority());
//        System.out.println(Role.ADMIN.toString().equals(Role.ADMIN.getAuthority()));
//    }
}
