package com.fernando.nieto.acciona.infrastructure.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.fernando.nieto.acciona.infrastructure.repository.entity")
@EnableJpaRepositories(basePackages = "com.fernando.nieto.acciona.infrastructure.repository")
public class RepositoryConfig {
}
