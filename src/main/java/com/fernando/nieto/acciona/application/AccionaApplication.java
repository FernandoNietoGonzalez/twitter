package com.fernando.nieto.acciona.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.fernando.nieto.acciona")
@EnableConfigurationProperties
public class AccionaApplication {

    static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(AccionaApplication.class, args);
    }

}
