package com.ilan;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QueryDslSqlApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(QueryDslSqlApplication.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.LOG)
                .build();
        ConfigurableApplicationContext applicationContext = springApplication.run(args);

        System.exit(SpringApplication.exit(applicationContext , () -> 0));
    }
}