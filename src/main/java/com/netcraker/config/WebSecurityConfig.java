/*
 * Copyright
 */

package com.netcraker.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Security Configuration.
 *
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * DataSource field.
     */
    private final DataSource datasource;

    /**
     * Dependency injection through the constructor.
     *
     * @param datasource Data Source
     */
    WebSecurityConfig(final DataSource datasource) {
        this.datasource = datasource;
    }

    // @checkstyle DesignForExtensionCheck (4 lines) The method is not intended to be shared
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected final void configure(final HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/register").not().fullyAuthenticated()
            .antMatchers("/index", "/product", "/cart", "/getUser").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/index")
            .permitAll();
    }

    @Override
    protected final void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(this.datasource)
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .usersByUsernameQuery("SELECT login, password, active from userscafe where login=?")
            .authoritiesByUsernameQuery("SELECT login, role from userscafe where login=?");
    }

}
