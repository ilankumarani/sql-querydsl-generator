package com.ilan;

import com.ilan.shutdown.ShutdownEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@SpringBootApplication
public class QueryDslSqlApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(QueryDslSqlApplication.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.LOG)
                .build();
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        /*Boolean isExit = Boolean.parseBoolean(System.getProperty("kill.system.exit"));
        log.info("is System.exit ? :: {}", isExit);
        if (!isExit) {
            log.info("JVM is System.exit(0)");
            System.exit(SpringApplication.exit(applicationContext, () -> 0));
        }*/

    }

    @DependsOn({"queryDslSqlGenerator"})
    @Bean
    @ConditionalOnProperty(name = "query.dsl.sql.kill.enabled", havingValue = "true")
    public CommandLineRunner shutdown(ShutdownEndpoint shutdownEndpoint) {
        return args -> {
            ShutdownEndpoint.ShutdownDescriptor shutdownDescriptor = shutdownEndpoint.shutdown();
            log.info("Application killed :: {}", shutdownDescriptor.getMessage());
        };
    }
}