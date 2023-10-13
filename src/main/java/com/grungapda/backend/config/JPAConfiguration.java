package com.grungapda.backend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.grungapda.backend")
@EnableJpaRepositories(basePackages = "com.grungapda.backend")
public class JPAConfiguration {
}
