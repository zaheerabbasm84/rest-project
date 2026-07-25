package com.restDemo.config;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class JdbiConfig {

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        // Automatically wires into Spring Boot's auto-configured DataSource
        Jdbi jdbi = Jdbi.create(dataSource);

        // Installs the SQL Object plugin for interface-driven DAOs
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi;
    }
}
