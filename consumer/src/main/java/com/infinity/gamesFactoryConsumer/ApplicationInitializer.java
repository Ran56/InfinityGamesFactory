package com.infinity.gamesFactoryConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com.infinity.gamesFactoryConsumer")
public class ApplicationInitializer {
    public static void main(String[] args){

        SpringApplication.run(ApplicationInitializer.class, args);
    }
}
