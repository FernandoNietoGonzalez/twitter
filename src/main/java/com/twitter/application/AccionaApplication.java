package com.twitter.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.twitter")
@EnableConfigurationProperties
public class AccionaApplication {

    static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(AccionaApplication.class, args);
    }

}
