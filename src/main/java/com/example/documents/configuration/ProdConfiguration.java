package com.example.documents.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class ProdConfiguration {

    @Bean
    public DataSource dataSource(){
        DataSource dataSource = new SingleConnectionDataSource();
        ((SingleConnectionDataSource) dataSource).setUrl("jdbc:postgres://localhost:5432/documents");
        ((SingleConnectionDataSource) dataSource).setDriverClassName("com.postgresql.Driver");
        ((SingleConnectionDataSource) dataSource).setUsername("richard");
        ((SingleConnectionDataSource) dataSource).setPassword("test123");
        return dataSource;
    }
}
