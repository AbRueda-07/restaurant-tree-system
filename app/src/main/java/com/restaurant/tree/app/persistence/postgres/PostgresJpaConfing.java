package com.restaurant.tree.app.persistence.postgres;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@Profile("postgres")
@EnableJpaRepositories(basePackages = "com.restaurant.tree.app.persistence.postgres")
@EntityScan(basePackages = "com.restaurant.tree.app.persistence.entity")
public class PostgresJpaConfing {
    // Se activa normal en Postgres
}

// Esta mini-clase solo se activa si NO estamos en el perfil de postgres 
// y apaga el DataSource de forma segura para que memory corra.
@Configuration
@Profile("!postgres")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class ShutDownJPAForMemoryConfig {
}