package xio.ilan.test;


import com.ilan.QueryDslSqlApplication;
import com.ilan.config.DbConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {QueryDslSqlApplication.class})
@Import({DbConfig.class})
@ActiveProfiles("packages")
@DisplayName("Generate in package beta.querydsl.sql")
public class GenerateInPackagesTest {

    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {

    }
}
