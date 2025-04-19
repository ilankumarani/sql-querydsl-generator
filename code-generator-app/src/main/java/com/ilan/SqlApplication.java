package com.ilan;

import com.ilan.dialect.CustomH2Dialect;
import com.ilan.shutdown.ShutdownEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import static io.ilan.config.QueryDslSqlGeneratorService.BEAN_NAME;

/**
 * Main class
 */
@Slf4j
@SpringBootApplication
public class SqlApplication {

    /**
     * JpaVendorAdapter when JpaVendorAdapter is missing
     *
     * @return return JpaVendorAdapter
     */
    @ConditionalOnMissingBean(JpaVendorAdapter.class)
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(Boolean.TRUE);
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        hibernateJpaVendorAdapter.setDatabasePlatform(CustomH2Dialect.class.getName());
        return hibernateJpaVendorAdapter;
    }

    /**
     * Main method of Java
     *
     * @param args for Main method
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(SqlApplication.class)
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

    /**
     * Manual shutdown of IOC container
     *
     * @param shutdownEndpoint shutdownEndpoint
     * @return CommandLineRunner
     */

    @DependsOn({BEAN_NAME})
    @Bean
    @ConditionalOnProperty(name = "query.dsl.sql.kill.enabled", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner shutdown(ShutdownEndpoint shutdownEndpoint) {
        return args -> {
            ShutdownEndpoint.ShutdownDescriptor shutdownDescriptor = shutdownEndpoint.shutdown();
            log.info("Application killed :: {}", shutdownDescriptor.getMessage());
        };
    }
}