package com.fernando.nieto.acciona.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .produces(applicationJson())
                .consumes(applicationJson()).select()
                .apis(basePackage("com.fernando.nieto.acciona.application.controller")).paths(any()).build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfo("Acciona", "book management", "1.0", null, new Contact("Fernando", null, "plataforma_notificaciones@viajeseci.es"), null, null, new ArrayList<>());
    }
    private Set<String> applicationJson() {
        return Stream.of("application/json").collect(toSet());
    }
}