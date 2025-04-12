package com.ilan;

import io.ilan.service.SqlExporterService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class GenerateSqlDslApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GenerateSqlDslApplication.class);
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);

        //System.exit(SpringApplication.exit(new SpringApplicationBuilder(GenerateSqlDslApplication.class).web(WebApplicationType.NONE).build().run(args), () -> 0));
    }


    @Bean
    public CommandLineRunner sqlQueryDslGenerator(DataSource dataSource, SqlExporterService sqlExporterService) {
        return args -> {
            sqlExporterService.exporter(dataSource.getConnection());
        };
    }
}