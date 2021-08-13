/*
 * Copyright
 */

package com.netcraker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *Model-View-Controller Config.
 *
 * @since 0.0.1
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public final void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
    }
}
