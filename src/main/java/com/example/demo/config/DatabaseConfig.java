package com.example.demo.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DatabaseConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create()
        .url(System.getenv("DATABASE_URL"))
        .username(System.getenv("DATABASE_USERNAME"))
        .password(System.getenv("DATABASE_PASSWORD"))
        .driverClassName("org.postgresql.Driver")
        .build();
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
