package com.infinity.gamesFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"com.infinity.gamesFactory"})
@ServletComponentScan(basePackages = {"com.infinity.gamesFactory.filter"})
public class ApplicationBootstrap {

    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationBootstrap.class,args);
    }


}
