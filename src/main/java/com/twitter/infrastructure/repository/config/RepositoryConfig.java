package com.twitter.infrastructure.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.twitter.infrastructure.repository.entity")
@EnableJpaRepositories(basePackages = "com.twitter.infrastructure.repository")
public class RepositoryConfig {
}
