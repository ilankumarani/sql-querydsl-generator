package io.ilan.config;

import io.ilan.service.SqlExporterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class QueryDslSqlGeneratorService {

    @Bean
    @ConditionalOnProperty(name = "query.dsl.sql.generation.enabled", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner queryDslSqlGenerator(DataSource dataSource, SqlExporterService sqlExporterService) {
        return args -> {
            sqlExporterService.exporter(dataSource.getConnection());
        };
    }
}
