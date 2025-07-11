package io.ilan.config;

import io.ilan.service.SqlExporterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import static io.ilan.config.QueryDslSqlGeneratorService.BEAN_NAME;

/**
 * Generate the QueryDsl from Entity CommandLine runner
 */
@Slf4j
@Configuration(BEAN_NAME)
@DependsOnDatabaseInitialization
public class QueryDslSqlGeneratorService {

    /**
     * Bean name for this class
     */
    public static final String BEAN_NAME = "SqlGeneratorService";

    /**
     * Generate the QueryDsl from Entity CommandLine runner
     *
     * @param dataSource         dataSource
     * @param sqlExporterService SqlExporter Service
     * @return nothing just run the bean
     */
    @Bean
    @ConditionalOnProperty(name = "query.dsl.sql.generation.enabled", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner queryDslSqlGenerator(DataSource dataSource, SqlExporterService sqlExporterService) {
        return args -> {
            log.info("############ Starting to export the Sql Class############");
            sqlExporterService.exporter(dataSource.getConnection());
        };
    }
}
