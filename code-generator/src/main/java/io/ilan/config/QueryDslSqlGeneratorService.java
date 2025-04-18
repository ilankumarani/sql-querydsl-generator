package io.ilan.config;

import io.ilan.service.SqlExporterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static io.ilan.config.QueryDslSqlGeneratorService.BEAN_NAME;

@Configuration(BEAN_NAME)
public class QueryDslSqlGeneratorService {

    public static final String BEAN_NAME = "SqlGeneratorService";

    @Bean
    @ConditionalOnProperty(name = "query.dsl.sql.generation.enabled", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner queryDslSqlGenerator(DataSource dataSource, SqlExporterService sqlExporterService) {
        return args -> {
            sqlExporterService.exporter(dataSource.getConnection());
        };
    }
}
