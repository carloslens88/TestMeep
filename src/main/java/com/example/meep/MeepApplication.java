package com.example.meep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MeepApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeepApplication.class, args);
    }

}
