package com.geelaro.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableCaching
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
