package io.ilan.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class DbConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Value("${jpa.entities.base-package:}") String[] entityBasePackages, EntityManagerFactoryBuilder builder, DataSource dataSource) {
        String[] packagesToScan = Arrays.stream(entityBasePackages).map(String::trim).toArray(String[]::new);
        return builder
                .dataSource(dataSource)
                .packages(packagesToScan)
                .build();
    }
}
