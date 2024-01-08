package com.example.alarmbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AlarmbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmbotApplication.class, args);
    }

}
