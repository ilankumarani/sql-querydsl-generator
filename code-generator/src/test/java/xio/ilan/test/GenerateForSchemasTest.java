package xio.ilan.test;

import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import xio.ilan.Application;
import xio.ilan.config.DbConfig;
import xio.ilan.sql.query.dsl.BBlogDetails;
import xio.ilan.sql.query.dsl.SBlogDetails;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {Application.class})
@Import({DbConfig.class})
@ActiveProfiles("schemas")
@DisplayName("Generate for BLOG,OWNER SCHEMA")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class GenerateForSchemasTest {

    private final ApplicationContext applicationContext;
    private final SQLQueryFactory sqlQueryFactory;
    private SBlogDetails sBlogDetails = SBlogDetails.blogDetails;

    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {
        List<BBlogDetails> blogDetails = sqlQueryFactory.select(Projections.bean(BBlogDetails.class,
                        sBlogDetails.content))
                .from(sBlogDetails)
                .fetch();

        assertEquals(50, blogDetails.size());
    }


    @Test
    public void countOfDataSource() {
        applicationContext.getBean(DataSource.class);
        Map<String, DataSource> beans = applicationContext.getBeansOfType(DataSource.class);
        assertEquals(1, beans.size());
    }
}
