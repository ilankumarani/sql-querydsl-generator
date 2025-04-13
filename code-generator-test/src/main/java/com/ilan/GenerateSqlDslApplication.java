package com.ilan;

import io.ilan.service.SqlExporterService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class GenerateSqlDslApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(GenerateSqlDslApplication.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.LOG)
                .build();

        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        System.exit(SpringApplication.exit(applicationContext , () -> 0));
    }


    @Bean
    public CommandLineRunner sqlQueryDslGenerator(DataSource dataSource, SqlExporterService sqlExporterService) {
        return args -> {
            sqlExporterService.exporter(dataSource.getConnection());
        };
    }
}