package com.restaurant.tree.app.config;

import java.util.Set;

import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class StorageAutoConfigurationImportFilter implements AutoConfigurationImportFilter, EnvironmentAware {

    private static final Set<String> JPA_AUTO_CONFIGURATIONS = Set.of(
            "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
            "org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration",
            "org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration");

    private static final Set<String> MONGO_AUTO_CONFIGURATIONS = Set.of(
            "org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration",
            "org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration",
            "org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration");

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
        String storage = environment.getProperty("app.storage", "memory");
        boolean[] matches = new boolean[autoConfigurationClasses.length];

        for (int i = 0; i < autoConfigurationClasses.length; i++) {
            String autoConfiguration = autoConfigurationClasses[i];
            matches[i] = isEnabledForStorage(autoConfiguration, storage);
        }

        return matches;
    }

    private boolean isEnabledForStorage(String autoConfiguration, String storage) {
        if (autoConfiguration == null) {
            return true;
        }

        if (!"postgres".equalsIgnoreCase(storage) && JPA_AUTO_CONFIGURATIONS.contains(autoConfiguration)) {
            return false;
        }

        if (!"mongo".equalsIgnoreCase(storage) && MONGO_AUTO_CONFIGURATIONS.contains(autoConfiguration)) {
            return false;
        }

        return true;
    }
}
