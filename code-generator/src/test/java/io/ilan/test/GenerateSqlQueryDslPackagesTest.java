package io.ilan.test;

import io.ilan.GenerateSqlDslApplication;
import io.ilan.config.DbConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {GenerateSqlDslApplication.class})
@Import({DbConfig.class})
@ActiveProfiles("packages")
@DisplayName("Multiple packages-scan for entities")
public class GenerateSqlQueryDslPackagesTest {

    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {

    }
}
