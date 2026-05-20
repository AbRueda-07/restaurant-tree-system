package com.restaurant.tree.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Value("${app.storage}")
    private String storageType;

    public String getStorageType() {

        return storageType;
    }
}