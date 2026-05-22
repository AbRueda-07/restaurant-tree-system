package com.restaurant.tree.app.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurant.tree.engine.collections.CollectionsTreeStrategy;
import com.restaurant.tree.engine.custom.CustomTreeStrategy;
import com.restaurant.tree.engine.strategy.TreeAlgorithmStrategy;

@Configuration
public class TreeStrategyConfig {

    @Bean
    @ConditionalOnProperty(
            name = "app.tree-strategy",
            havingValue = "custom",
            matchIfMissing = true
    )
    public TreeAlgorithmStrategy customTreeStrategy() {
        return new CustomTreeStrategy();
    }

    @Bean
    @ConditionalOnProperty(
            name = "app.tree-strategy",
            havingValue = "collections"
    )
    public TreeAlgorithmStrategy collectionsTreeStrategy() {
        return new CollectionsTreeStrategy();
    }
}