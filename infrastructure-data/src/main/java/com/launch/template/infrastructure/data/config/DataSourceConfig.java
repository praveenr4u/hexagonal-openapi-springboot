package com.launch.template.infrastructure.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.launch.template.infrastructure.data")
@EnableJpaRepositories("com.launch.template.infrastructure.data")
@EnableJpaAuditing
public class DataSourceConfig {
}
