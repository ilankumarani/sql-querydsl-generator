package com.ilan.config;


import jakarta.persistence.EntityManagerFactory;
import org.ilan.annotation.AbdEnableJpaRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;


/**
 * Db configuration
 */
@AbdEnableJpaRepositories(basePackages = "${jpa.repositories.base-packages}")
@Configuration
@ConditionalOnProperty(name = "query.dsl.sql.custom.db.config.enabled", havingValue = "true", matchIfMissing = true)
public class CustomDbConfig {

    private static final Logger log = LoggerFactory.getLogger(CustomDbConfig.class);

    /**
     * Load the spring.datasource properties
     *
     * @return DataSourceProperties
     */
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Custom entity scan, so custom entityManager
     *
     * @param entityBasePackages entity base-packages
     * @param builder            EntityManagerFactoryBuilder builder
     * @param dataSource         DataSource bean
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Value("${jpa.entities.base-packages:}") String[] entityBasePackages, EntityManagerFactoryBuilder builder, DataSource dataSource) {
        String[] packagesToScan = Arrays.stream(entityBasePackages).map(String::trim).toArray(String[]::new);
        Arrays.stream(packagesToScan).forEach(entityBasePackage -> {
            log.info("Entity package to scan :: {}", entityBasePackage.toString());
        });
        return builder
                .dataSource(dataSource)
                .packages(packagesToScan)
                .build();
    }

    /**
     * PlatformTransactionManager bean
     *
     * @param entityManagerFactory entityManagerFactory
     * @return PlatformTransactionManager
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
