package com.example.springdbreplication.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create()
            .type(HikariDataSource.class)
            .build();
    }

    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public DataSource routingDataSource(
        @Qualifier("primaryDataSource") DataSource primaryDataSource,
        @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        RoutingDataSource routingDataSourceImpl = new RoutingDataSource();
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("primary", primaryDataSource);
        targetDataSource.put("secondary", secondaryDataSource);
        routingDataSourceImpl.setTargetDataSources(targetDataSource);
        routingDataSourceImpl.setDefaultTargetDataSource(primaryDataSource);
        return routingDataSourceImpl;
    }

}
