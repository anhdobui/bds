package com.example.bds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdsApplication.class, args);
    }

}
