package xio.ilan.test;

import com.ilan.GenerateSqlDslApplication;
import com.ilan.config.DbConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE, classes = {GenerateSqlDslApplication.class})
@Import({DbConfig.class})
@ActiveProfiles("directory")
@DisplayName("Single package-scan for entities")
public class GenerateInDirectoryTest {


    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {

    }
}
