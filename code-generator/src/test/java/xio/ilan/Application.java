package xio.ilan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import xio.ilan.service.QueryDslService;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplicationBuilder(Application.class)
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

    @Bean
    @DependsOnDatabaseInitialization
    @ConditionalOnProperty(name = "query.dsl.data.load.enabled", havingValue = "true", matchIfMissing = true)
    public CommandLineRunner loadData(QueryDslService queryDslService) {
        return args -> queryDslService.loadData();
    }

}