package com.vam.cco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.vam.cco")
public class JpaConfig {
    // Additional JPA configurations can be added here if needed
}
