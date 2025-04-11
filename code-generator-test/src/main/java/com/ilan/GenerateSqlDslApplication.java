package com.ilan;

import io.ilan.service.SqlExporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class GenerateSqlDslApplication {

    @Autowired
    private SqlExporterService sqlExporterService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GenerateSqlDslApplication.class);
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);
    }


    @Bean
    public CommandLineRunner sqlQueryDslGenerator(DataSource dataSource) {
        return args -> {
            sqlExporterService.exporter(dataSource.getConnection());
        };
    }
}