package com.restDemo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.restDemo.tenant.CurrentTenantIdentifierResolverImpl;
import com.restDemo.tenant.MultiTenantConnectionProviderImpl;

@Configuration
public class HibernateConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            DataSource dataSource,
            MultiTenantConnectionProviderImpl connectionProvider,
            CurrentTenantIdentifierResolverImpl tenantResolver) {

        Map<String, Object> properties = new HashMap<>();

        properties.put(
                AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER,
                connectionProvider);

        properties.put(
                AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER,
                tenantResolver);

        return builder
                .dataSource(dataSource)
                .packages("com.restDemo")
                .properties(properties)
                .build();
    }

}
