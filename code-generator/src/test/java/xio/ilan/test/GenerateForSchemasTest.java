package xio.ilan.test;

import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import xio.ilan.H2TestApplication;
import xio.ilan.config.DbConfig;
import xio.ilan.service.QueryDslService;
import xio.ilan.sql.query.dsl.BBlogDetails;
import xio.ilan.sql.query.dsl.SBlogDetails;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {H2TestApplication.class})
@Import({DbConfig.class})
@ActiveProfiles("schemas")
@DisplayName("Generate for BLOG,OWNER SCHEMA")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenerateForSchemasTest {

    private final ApplicationContext applicationContext;
    private final SQLQueryFactory sqlQueryFactory;
    private final QueryDslService queryDslService;
    private SBlogDetails sBlogDetails = SBlogDetails.blogDetails;

    @BeforeAll
    public void init(){
        queryDslService.loadData();
    }

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
