package com.example.spring01.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.brad")
    public DataSource bradDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    @Primary
    public NamedParameterJdbcTemplate bradJdbc(
            @Qualifier("bradDataSource") DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
