package com.example.documents.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Component
public class MeinHealthCheck implements HealthIndicator {

    @Autowired
    private DataSource dataSource;

    public Health health() {
        try{
            return dataSource.getConnection() != null ? Health.up().build() : Health.down().build();
        } catch (Exception e){
            return Health.down().build();
        }
    }
}
