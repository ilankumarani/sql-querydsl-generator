package xio.ilan.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import xio.ilan.Application;
import xio.ilan.config.DbConfig;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE,
        classes = {Application.class})
@Import({DbConfig.class})
@ActiveProfiles("table")
@DisplayName("Generate for Domains table")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class GenerateForTableTest {

    @DisplayName("Generate SQL QueryDsl")
    @Test
    public void test() {

    }
}
