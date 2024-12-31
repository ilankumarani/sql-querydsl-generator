package io.ilan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenerateSqlDslApplication {

    private static final Logger log = LoggerFactory.getLogger(GenerateSqlDslApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GenerateSqlDslApplication.class);
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);
        log.info("########## COMPLETED ##########");
    }
}