package xio.ilan;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import com.querydsl.sql.types.LocalDateTimeType;
import com.querydsl.sql.types.LocalDateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

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
    public com.querydsl.sql.Configuration querydslConfiguration() {
        SQLTemplates templates = H2Templates.builder()
                .printSchema() // to include the schema in the output
                //.quote()       // to quote names
                .newLineToSingleSpace() // to replace new lines with single space in the output
                //.escape(ch)    // to set the escape char
                .build();
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration (templates);
        configuration.setExceptionTranslator (new SpringExceptionTranslator());
        configuration.register(new LocalDateTimeType());
        configuration.register(new LocalDateType());
        configuration.setUseLiterals(Boolean.TRUE);
        return configuration;
    }


    @Bean
    public SQLQueryFactory sqlQueryFactory(DataSource dataSource, com.querydsl.sql.Configuration configuration) {
        SpringConnectionProvider provider = new SpringConnectionProvider(dataSource);
        //return new SQLQueryFactory(configuration, provider);
        return new SQLQueryFactory(configuration, new TransactionAwareDataSourceProxy(dataSource));
    }
}