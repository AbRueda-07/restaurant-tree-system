package com.restaurant.tree.app.persistence.postgres;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@Profile("postgres")
@EnableJpaRepositories(basePackages = "com.restaurant.tree.app.persistence.postgres")
@EntityScan(basePackages = "com.restaurant.tree.app.persistence.entity")
public class PostgresJpaConfig {

}