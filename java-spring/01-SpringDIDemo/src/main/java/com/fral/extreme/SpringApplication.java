package com.fral.extreme;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication annotation scans all the packages of the project, but we also
 * can specify it manually like so: @SpringBootApplication(scanBasePackageClasses = {comma, separated, classes})
 */
@SpringBootApplication
public class SpringApplication {

    public static void main (String args[]) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }
}
