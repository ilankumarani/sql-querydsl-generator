package xio.ilan.test;


import com.ilan.QueryDslSqlApplication;
import com.ilan.config.DbConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {QueryDslSqlApplication.class})
@Import({DbConfig.class})
@DisplayName("Generate for all Schema")
public class GenerateTest {

    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {

    }
}
