/*
 * Copyright
 */
package com.netcraker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the project.
 * Suppress warning because class 'SpringBootStarter' may be without abstract method.
 *
 * @since 0.0.1
 */
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
@SpringBootApplication
public abstract class SpringBootStarter {

    /**
     * Entry point into the application.
     * Suppress warning because it's entry point.
     *
     * @param args Arguments
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
    public static void main(final String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

}
