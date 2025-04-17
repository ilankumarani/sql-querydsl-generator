package xio.ilan.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Arrays;


@EnableJpaRepositories(basePackages = {"com.entity,com.ilan.entity", "org.ilan.entity"})
@Configuration
public class DbConfig {
    private static final Logger log = LoggerFactory.getLogger(DbConfig.class);

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Value("${jpa.entities.base-packages:}") String[] entityBasePackages, EntityManagerFactoryBuilder builder, DataSource dataSource) {
        String[] packagesToScan = Arrays.stream(entityBasePackages).map(String::trim).toArray(String[]::new);
        Arrays.stream(packagesToScan).forEach(entityBasePackage->{
            log.info("Entity package to scan :: {}", entityBasePackage.toString());
        });
        return builder
                .dataSource(dataSource)
                .packages(packagesToScan)
                .build();
    }
}
